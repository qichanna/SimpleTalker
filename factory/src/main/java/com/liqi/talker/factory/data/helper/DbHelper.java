package com.liqi.talker.factory.data.helper;

import com.liqi.talker.factory.model.db.AppDatabase;
import com.liqi.talker.factory.model.db.GroupMember;
import com.liqi.talker.factory.model.db.Message;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** 数据库的辅助工具类
 *  辅助完成: 增删改
 * Created by liqi on 2017/10/5.
 */

public class DbHelper {
    private static final DbHelper instance;
    static {
        instance = new DbHelper();
    }

    private DbHelper(){
//        save(new User());
//        save(new User(),new User(),new User());
//        save(new User[]{new User(),new User(),new User()});
    }

    /**
     *  观察者集合
     *  Class<?>: 观察的表
     *  Set<ChangedListener>: 每一个表对应的观察者有很多
     */
    private final Map<Class<?>,Set<ChangedListener>> changeListeners = new HashMap<>();

    /**
     *  从所有的监听者中，获取某一个表的所有监听者
     * @param modelClass modelClass 表对应的信息
     * @param <Model>范型
     * @return Set<ChangedListener>
     */
    private <Model extends BaseModel> Set<ChangedListener> getListeners(Class<Model> modelClass){
        if(changeListeners.containsKey(modelClass)){
            return changeListeners.get(modelClass);
        }
        return null;
    }

    /**
     *  添加一个监听
     * @param tClass 对某个表关注
     * @param listener 监听者
     * @param <Model> 表的范型
     */
    public static <Model extends BaseModel> void addChangedListener(final Class<Model> tClass, ChangedListener<Model> listener){
        Set<ChangedListener> changedListeners = instance.getListeners(tClass);
        if(changedListeners == null){
            // 初始化某一类型的容器
            changedListeners = new HashSet<>();
            // 添加到总的Map
            instance.changeListeners.put(tClass,changedListeners);
        }
        changedListeners.add(listener);
    }

    /**
     *  删除某一个表的监听器
     * @param tClass 表
     * @param listener 监听器
     * @param <Model> 表的范型
     */
    public static <Model extends BaseModel> void removeChangedListener(final Class<Model> tClass, ChangedListener<Model> listener){
        Set<ChangedListener> changedListeners = instance.getListeners(tClass);
        if(changedListeners == null){
            // 容器本身为null，代表根本就没有
            return;
        }
        // 从容器中删除这个监听者
        changedListeners.remove(listener);
    }

    // 限定条件是BaseModel

    /**
     *  新增或者修改的统一方法
     * @param tClass 传递一个CLass信息
     * @param models 这个Class对应的实例的数组
     * @param <Model> 这个实例的范型，限定条件是BaseModel
     */
    public static<Model extends BaseModel> void save(final  Class<Model> tClass, final Model... models){
        if(models == null || models.length == 0)
            return;

        // 当前数据库的一个管理者
        DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
        // 提交一个事物
        definition.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                // 执行
                ModelAdapter<Model> adapter = FlowManager.getModelAdapter(tClass);
                // 保存
                adapter.saveAll(Arrays.asList(models));
                // 唤起通知
                instance.notifySave(tClass,models);
            }
        }).build().execute();
    }

    /**
     * 进行删除数据库的统一封装方法
     * @param tClass 传递一个CLass信息
     * @param models 这个Class对应的实例的数组
     * @param <Model> 这个实例的范型，限定条件是BaseModel
     */
    public static<Model extends BaseModel> void delete(final  Class<Model> tClass, final Model... models){
        if(models == null || models.length == 0)
            return;

        // 当前数据库的一个管理者
        DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
        // 提交一个事物
        definition.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                // 执行
                ModelAdapter<Model> adapter = FlowManager.getModelAdapter(tClass);
                // 删除
                adapter.deleteAll(Arrays.asList(models));
                // 唤起通知
                instance.notifyDelete(tClass,models);
            }
        }).build().execute();
    }

    /**
     *  进行通知调用
     * @param tClass 通知的类型
     * @param models 通知的Model数组
     * @param <Model> 这个实例的范型，限定条件是BaseModel
     */
    @SuppressWarnings("unchecked")
    private final  <Model extends BaseModel> void notifySave(final  Class<Model> tClass, final Model... models){
        // 找监听器
        final Set<ChangedListener> listeners = getListeners(tClass);
        if(listeners != null && listeners.size() > 0){
            // 通用的通知
            for (ChangedListener<Model> listener : listeners) {
                listener.onDateSave(models);
            }
        }

        // 例外情况
        if(GroupMember.class.equals(tClass)){
            // 群成员变更, 需要通知对应群信息更新

        }else if(Message.class.equals(tClass)){
            // 消息变化, 应该通知会话列表更新

        }
    }

    /**
     *  进行通知调用
     * @param tClass 通知的类型
     * @param models 通知的Model数组
     * @param <Model> 这个实例的范型，限定条件是BaseModel
     */
    private final  <Model extends BaseModel> void notifyDelete(final  Class<Model> tClass, final Model... models){
        // TODO
    }

    /**
     *  通知监听器
     * @param <Data>
     */
    public interface ChangedListener<Data>{
        void onDateSave(Data... list);

        void onDateDelete(Data... list);
    }
}

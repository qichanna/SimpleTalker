package com.liqi.talker.factory.data.helper;

import com.liqi.talker.factory.model.db.AppDatabase;
import com.liqi.talker.factory.model.db.User;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.Arrays;

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

    public static void save(final User... users){
        if(users == null || users.length == 0)
            return;

        // 当前数据库的一个管理者
        DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
        // 提交一个事物
        definition.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                // 执行
                ModelAdapter<User> adapter = FlowManager.getModelAdapter(User.class);
//                adapter.saveAll(CollectionUtil.toArrayList(users));
                adapter.saveAll(Arrays.asList(users));
            }
        }).build().execute();
    }

    public static<Model> void save(final  Class<Model> tClass,final Model... models){

    }
}

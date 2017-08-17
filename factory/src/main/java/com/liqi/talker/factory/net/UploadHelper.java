package com.liqi.talker.factory.net;

import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.liqi.talker.factory.Factory;

/**上传工具类，用于上次任意文件到阿里OSS存储
 * Created by liqi on 2017/8/17.
 */

public class UploadHelper {
    // 与你们的存储区域有关系
    private static final String ENDPOINT = "http://oss-cn-hongkong.aliyuncs.com";
    // 上传的仓库名
    private static final String BUCKET_NAME = "italker-new";

    private static OSS getClient() {
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(
                "LTAIYQD07p05pHQW", "2txxzT8JXiHKEdEjylumFy6sXcDQ0G");
        return new OSSClient(Factory.app(), ENDPOINT, credentialProvider);
    }
}

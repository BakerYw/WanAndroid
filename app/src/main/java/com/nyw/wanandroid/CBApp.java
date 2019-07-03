package com.nyw.wanandroid;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bakerj.rxretrohttp.RxRetroHttp;
import com.blankj.utilcode.util.Utils;
import com.nyw.domain.common.api.WanApiResult;
import com.nyw.domain.common.api.DownloadInterceptor;
import com.nyw.domain.common.api.HeaderInterceptor;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

public class CBApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        libInit();
        thirdInit();
    }

    private void libInit(){
        ARouter.init(this);
        Utils.init(this);
        RxRetroHttp.init(this, BuildConfig.DEBUG)
                .setBaseUrl(BuildConfig.API_SERVER_UPLOAD)
                .setApiResultClass(WanApiResult.class)
                .addInterceptor(new DownloadInterceptor())
                .generateRetroClient(BuildConstants.RETRO_CLIENT_TAG_FILE);
        RxRetroHttp.getInstance().resetInterceptor()
                .setBaseUrl(BuildConfig.API_SERVER_URL)
                .setApiResultClass(WanApiResult.class)
                .setTimeOut(BuildConfig.API_TIME_OUT)
                .addInterceptor(new HeaderInterceptor())
                .setDefaultErrMsg("服务器开小差了")
                .generateRetroClient();
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            MaterialHeader materialHeader = new MaterialHeader(context);
            materialHeader.setColorSchemeColors(context.getResources().getColor(R.color
                    .colorPrimary));
            return materialHeader;
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            ClassicsFooter classicsFooter = new ClassicsFooter(context);
            classicsFooter.setFinishDuration(0);
            return classicsFooter;
        });
    }

    private void thirdInit() {

    }
}

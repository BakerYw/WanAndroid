package com.nyw.wanandroid.utils.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.blankj.utilcode.util.StringUtils;
import com.nyw.domain.domain.event.IInterceptEvent;
import com.nyw.domain.domain.event.InterceptJumpEvent;
import com.nyw.domain.domain.event.home.ToUserCenterEvent;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.domain.domain.router.RouterInterceptorConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BakerJ
 * @date 2017/12/28
 * 登录拦截器
 */
@Interceptor(priority = RouterInterceptorConstants.PRIORITY_LOGIN, name =
        RouterInterceptorConstants.NAME_LOGIN)
public class LoginInterceptor implements IInterceptor {
    //特定路径所对应的拦截后跳转事件
    private static final Map<String, IInterceptEvent> LOGIN_INTERCEPT_EVENT_MAP = new HashMap<>();

    {
        LOGIN_INTERCEPT_EVENT_MAP.put(PathConstants.PATH_ME, new ToUserCenterEvent());
    }

    private static boolean pathNeedLogin(String path) {
        for (String p : PathConstants.LOGIN_REQUIRED_PATH) {
            if (p.equals(path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        //判断登录
        if (pathNeedLogin(postcard.getPath())) {
            callback.onInterrupt(new Throwable("需要登录"));
            //获取特定登录后跳转事件
            IInterceptEvent event = LOGIN_INTERCEPT_EVENT_MAP.get(postcard.getPath());
            if (event == null) {
                //如果不存在则新建事件，内容为原始跳转目标页信息
                event = new InterceptJumpEvent();
                ((InterceptJumpEvent) event).setPath(postcard.getPath());
                ((InterceptJumpEvent) event).setBundle(postcard.getExtras());
            }
            //跳转到登录
            Navigation.navigateToLogin(event);
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}

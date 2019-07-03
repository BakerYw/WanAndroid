package com.nyw.domain.common.util.cache;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.nyw.domain.domain.bean.response.user.UserInfo;
import com.nyw.domain.domain.event.IInterceptEvent;
import com.nyw.domain.domain.event.login.LogOutEvent;
import com.nyw.domain.domain.event.login.LoginEvent;
import com.nyw.domain.domain.router.Navigation;

import org.greenrobot.eventbus.EventBus;

/**
 * @author BakerJ
 * @date 2017/12/21
 */

public class UserCacheUtil {
    private static final String SPNAME_USER_INFO = "SPNAME_USER_INFO";
    private static final String SPKEY_USER_INFO = "SPKEY_USER_INFO";
    private static final String SPKEY_EVENT_SIGN_INFO = "SPKEY_EVENT_SIGN_INFO";
    private static final String TOKEN = "TOKEN";

    public static void saveUserInfo(UserInfo userInfo) {
        SPUtils.getInstance(SPNAME_USER_INFO).put(SPKEY_USER_INFO, new Gson().toJson(userInfo));
    }

    public static UserInfo getUserInfo() {
        String userInfo = SPUtils.getInstance(SPNAME_USER_INFO).getString
                (SPKEY_USER_INFO, "");
        if (TextUtils.isEmpty(userInfo)) {
            UserInfo userInfoBean = new UserInfo();
            userInfoBean.setUuid("");
            return userInfoBean;
        }
        return new Gson().fromJson(userInfo, UserInfo.class);
    }

//    public static void saveToken(Token token) {
//        SPUtils.getInstance(SPNAME_USER_INFO).put(TOKEN, new Gson().toJson(token));
//        EventBus.getDefault().post(new LoginEvent());
//    }
//
//    public static String getToken() {
//        String token = SPUtils.getInstance(SPNAME_USER_INFO).getString(TOKEN, "");
//        if (!TextUtils.isEmpty(token)) {
//            Token tokenObj = new Gson().fromJson(token, Token.class);
//            token = tokenObj.getAccessToken();
//        }
//        return token;
//    }

//    public static boolean checkIsLoginWithJump(IInterceptEvent event) {
//        boolean isLogin = checkIsLoginWithoutJump();
//        if (!isLogin) {
//            Navigation.navigateToLogin(event);
//        }
//        return isLogin;
//    }

//    public static boolean checkIsLoginWithoutJump() {
//        return !StringUtils.isEmpty(getToken());
//    }

    public static void logOut(boolean isManual) {
        SPUtils.getInstance(SPNAME_USER_INFO).clear(true);
//        EventBus.getDefault().post(new UserInfoChangedEvent());
        EventBus.getDefault().post(new LogOutEvent(isManual));
    }
}

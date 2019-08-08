package com.nyw.domain.common.util.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.nyw.domain.common.Constants;
import com.nyw.domain.domain.bean.response.login.LoginInfo;
import com.nyw.domain.domain.event.IInterceptEvent;
import com.nyw.domain.domain.event.login.LogOutEvent;
import com.nyw.domain.domain.router.Navigation;

import org.greenrobot.eventbus.EventBus;

import static com.nyw.domain.common.Constants.ISLOGIN;

/**
 * @author nyw
 * @date 2019/07/25
 */
public class UserCacheUtil {
    /**
     * 保存登录名和密码
     */
    public static void saveLoginInfo(LoginInfo loginInfo) {
        SPUtils.getInstance(ISLOGIN).put(ISLOGIN,
                new Gson().toJson(loginInfo));
    }

    /**
     * 获取用户登录名和 密码
     * @return
     */
    public static LoginInfo getLoginInfo() {
        String loginInfo = SPUtils.getInstance(ISLOGIN).getString
                (ISLOGIN, "");
        if (TextUtils.isEmpty(loginInfo)) {
            return new LoginInfo();
        }
        return new Gson().fromJson(loginInfo, LoginInfo.class);
    }


    /**
     * 获取保存的用户名
     *
     * @return 用户名
     */
    public static String getLoginName() {
        LoginInfo loginInfo=getLoginInfo();
        return loginInfo.getLoginName();
    }

    /**
     * 获取保存的登录密码
     *
     * @param context Context
     * @return 登录密码
     */
    public static String getLoginPwd(Context context) {
        SharedPreferences sp = context.getSharedPreferences(ISLOGIN, Context.MODE_MULTI_PROCESS);
        return sp.getString("loginPwd", "");
    }


    /**
     * 获取本地保存的图片
     * @return
     */
    public static String getUserImage() {
        String userpic = SPUtils.getInstance().getString(Constants.USERPIC);
        return userpic;
    }

    public static boolean checkIsLoginWithJump(IInterceptEvent event) {
        boolean isLogin = checkIsLoginWithoutJump();
        if (!isLogin) {
            Navigation.navigateToLogin(event);
        }
        return isLogin;
    }

    public static boolean checkIsLoginWithoutJump() {
        return !StringUtils.isEmpty(getLoginName());
    }

    /**
     * 退出清除登录信息
     * @param isManual
     */
    public static void logOut(boolean isManual) {
        SPUtils.getInstance(ISLOGIN).clear(true);
        EventBus.getDefault().post(new LogOutEvent(isManual));
    }

}

package com.nyw.domain.common.util.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.nyw.domain.common.Constants;
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
     *
     * @param context Context
     * @param name    用户名
     * @param pwd     密码
     */
    public static void saveLoginInfo(Context context, String name, String pwd) {
        SharedPreferences sp = context.getSharedPreferences(ISLOGIN, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginName", name).putString("loginPwd", pwd).apply();
    }

    /**
     * 清除登录信息
     *
     * @param context Context
     */
    public static void clearLoginInfo(Context context,boolean isManual) {
        SharedPreferences sp = context.getSharedPreferences(ISLOGIN, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
        EventBus.getDefault().post(new LogOutEvent(isManual));
    }

    /**
     * 获取保存的用户名
     *
     * @param context Context
     * @return 用户名
     */
    public static String getLoginName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(ISLOGIN, Context.MODE_MULTI_PROCESS);
        return sp.getString("loginName", "");
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

    public static boolean checkIsLoginWithJump(IInterceptEvent event,Context context) {
        boolean isLogin = checkIsLoginWithoutJump(context);
        if (!isLogin) {
            Navigation.navigateToLogin(event);
        }
        return isLogin;
    }

    public static boolean checkIsLoginWithoutJump(Context context) {
        return !StringUtils.isEmpty(getLoginName(context));
    }

    public static void logOut(boolean isManual) {
        SPUtils.getInstance(ISLOGIN).clear(true);
        EventBus.getDefault().post(new LogOutEvent(isManual));
    }

}

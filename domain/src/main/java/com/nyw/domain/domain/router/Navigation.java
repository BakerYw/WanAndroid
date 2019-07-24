package com.nyw.domain.domain.router;

import android.net.Uri;
import android.text.TextUtils;

import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ActivityUtils;
import com.nyw.domain.common.Constants;
import com.nyw.domain.domain.event.IInterceptEvent;
import com.nyw.domain.domain.event.InterceptJumpEvent;
import com.nyw.libthird.sharelib.ThirdPartyLoginInfo;

/**
 * @author BakerJ
 * @date 2017/12/20
 */

public class Navigation {
    //common
    public static void urlNavigation(String url) {
        urlNavigation(url, null);
    }

    public static void urlNavigation(String url, ActivityOptionsCompat activityOptionsCompat) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        try {
            ARouter.getInstance().build(Uri.parse(url))
                    .withOptionsCompat(activityOptionsCompat)
                    .navigation();
        } catch (Exception e) {
            navigateToWeb(url, activityOptionsCompat);
        }
    }
    public static void navigateToSearch() {ARouter.getInstance().build(PathConstants.PATH_SEARCH).navigation(); }

    public static void navigateToSearchResult(String key) {ARouter.getInstance().build(PathConstants.PATH_SEARCH_RESULT).withString("key",key).navigation(); }


    public static void navigateToWeb(String url) {
        navigateToWeb(url, null);
    }

    public static void navigateToWeb(String url, ActivityOptionsCompat activityOptionsCompat) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        ARouter.getInstance().build(PathConstants.PATH_WEB_COMMON)
                .withOptionsCompat(activityOptionsCompat)
                .withString("url", url)
                .navigation();
    }

    public static void navigateToEvent(InterceptJumpEvent event) {
        ARouter.getInstance().build(event.getPath())
                .withBundle(Constants.JUMP_BUNDLE, event.getBundle())
                .navigation();

    }

    //welcome
    public static void navigateToWelcomeGuide(ActivityOptionsCompat activityOptionsCompat) {
        ARouter.getInstance().build(PathConstants.PATH_WELCOME_GUIDE)
                .withOptionsCompat(activityOptionsCompat)
                .navigation();
    }

    //login
    public static void navigateToLogin(IInterceptEvent event) {
        setEvent(ARouter.getInstance().build(PathConstants.PATH_LOGIN_PHONE), event).navigation();
    }

    public static void navigateToLoginBind(IInterceptEvent event, ThirdPartyLoginInfo third,
                                           String loginType) {
        setEvent(ARouter.getInstance().build(PathConstants.PATH_LOGIN_BIND), event)
                .withParcelable("third", third)
                .withString("loginType", loginType)
                .navigation();
    }

    public static void navigateToLoginPhone(IInterceptEvent event) {
        setEvent(ARouter.getInstance().build(PathConstants.PATH_LOGIN_PHONE), event).navigation();
    }

    public static void navigationToLoginPhoneWithThirdInfo(String thirdParty, String openId,
                                                           ThirdPartyLoginInfo thirdPartyLoginInfo,
                                                           IInterceptEvent event) {
        setEvent(ARouter.getInstance().build(PathConstants.PATH_LOGIN_PHONE), event)
                .withString("openId", openId)
                .withString("thirdParty", thirdParty)
                .withParcelable("thirdPartyInfo", thirdPartyLoginInfo)
                .navigation();
    }

    public static void navigateToLoginCode(String phone, IInterceptEvent event) {
        setEvent(ARouter.getInstance().build(PathConstants.PATH_LOGIN_CODE), event)
                .withString("phone", phone).navigation();
    }

    public static void navigateToLoginCodeWithThirdInfo(String phone, String thirdParty, String
            openId, ThirdPartyLoginInfo thirdPartyLoginInfo, IInterceptEvent event) {
        setEvent(ARouter.getInstance().build(PathConstants.PATH_LOGIN_CODE), event)
                .withString("phone", phone)
                .withString("openId", openId)
                .withString("thirdParty", thirdParty)
                .withParcelable("thirdPartyInfo", thirdPartyLoginInfo).navigation();
    }
//    public static void navigateToLogin() {
//        ARouter.getInstance().build(PathConstants.PATH_LOGIN).navigation();
//    }

    //home
    public static void navigateToHome(ActivityOptionsCompat activityOptionsCompat,
                                      NavigationCallback callback) {
        ARouter.getInstance().build(PathConstants.PATH_HOME)
                .withOptionsCompat(activityOptionsCompat)
                .navigation(ActivityUtils.getTopActivity(), callback);
    }

    //circle
    public static Fragment getCircleFragment() {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_CIRCLE).navigation();
    }

    public static void navigateToCirclePublish(NavigationCallback callback) {
        ARouter.getInstance().build(PathConstants.PATH_CIRCLE_PUBLISH)
                .navigation(ActivityUtils.getTopActivity(), callback);
    }

    public static void navigateToCircleCreate(NavigationCallback callback) {
        ARouter.getInstance().build(PathConstants.PATH_CIRCLE_CREATE)
                .navigation(ActivityUtils.getTopActivity(), callback);
    }

    public static void navigateToCircleOwner(NavigationCallback callback, long circleId) {
        ARouter.getInstance().build(PathConstants.PATH_CIRCLE_INFO)
                .withLong("circleId", circleId)
                .navigation(ActivityUtils.getTopActivity(), callback);
    }

    public static void navigateToOwnerCircle(NavigationCallback callback, long circleLeaderId,
                                             String name) {
        ARouter.getInstance().build(PathConstants.PATH_CIRCLE_OWNER_CIRCLE)
                .withLong("circleLeaderId", circleLeaderId).withString("name", name)
                .navigation(ActivityUtils.getTopActivity(), callback);
    }

    public static void navigateToCircleOperation(long circleStateId) {
        ARouter.getInstance().build(PathConstants.PATH_CIRCLE_OPERATION)
                .withLong("circleStateId", circleStateId).navigation();
    }

    //Knowledge
    public static Fragment getKnowledgeFragment() {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_KNOWLEDGE).navigation();
    }

    public static Fragment getArtFragment() {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_KNOWLEDG_ART)
                .navigation();
    }
    public static Fragment getNavFragment() {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_KNOWLEDG_NAV)
               .navigation();
    }

    //Wechat
    public static Fragment getWechatFragment() {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_WECHAT).navigation();
    }
    public static Fragment getWechatListFragment(int subcat) {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_WECHAT_LIST)
                .withInt("subcat", subcat).navigation(ActivityUtils.getTopActivity());
    }


    //Project
    public static Fragment getProjectFragment() {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_PROJECT).navigation();
    }

    public static Fragment getProjectListFragment(int subcat) {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_PROJECT_LIST)
                .withInt("subcat", subcat).navigation(ActivityUtils.getTopActivity());
    }


    public static void navigateToGoodDetail(long itemId) {
        ARouter.getInstance().build(PathConstants.PATH_GOOD_DETAIL)
                .withLong("itemId", itemId).navigation();
    }

    public static void navigateToOrder(String title, String status) {
        ARouter.getInstance().build(PathConstants.PATH_ORDER)
                .withString("title", title).withString("status", status).navigation();
    }

    public static Fragment getOrderListFragment(String status) {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_ORDER_LIST)
                .withString("status", status).navigation();
    }



    //me
    public static Fragment getMeFragment() {
        return (Fragment) ARouter.getInstance().build(PathConstants.PATH_ME).navigation();
    }

    //setting
    public static void navigateToSetting() {
        ARouter.getInstance().build(PathConstants.PATH_SETTING).navigation();
    }

    private static Postcard setEvent(Postcard postcard, IInterceptEvent event) {
        if (event != null) {
            postcard.withParcelable(RouterInterceptorConstants.POSTCARD_EVENT, event);
        }
        return postcard;
    }

    public static void navigateToWithdraw() {
        ARouter.getInstance().build(PathConstants.PATH_WITHDRAW).navigation();
    }
}

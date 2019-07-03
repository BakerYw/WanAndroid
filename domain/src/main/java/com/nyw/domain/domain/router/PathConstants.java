package com.nyw.domain.domain.router;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BakerJ
 * @date 2017/12/20
 */

public class PathConstants {
    public static final List<String> supportPath = new ArrayList<>();

    //scheme web view
    private static final String PATH_WEB_GROUP = "/web";
    public static final String PATH_WEB_COMMON = PATH_WEB_GROUP + "/common";

    //scheme welcome
    private static final String PATH_GROUP_WELCOME = "/welcome";
    public static final String PATH_WELCOME_GUIDE = PATH_GROUP_WELCOME + "/guide";

    //scheme login
    private static final String PATH_GROUP_LOGIN = "/login";
    public static final String PATH_LOGIN_INTRO = PATH_GROUP_LOGIN + "/intro";
    public static final String PATH_LOGIN_PHONE = PATH_GROUP_LOGIN + "/phone";
    public static final String PATH_LOGIN_CODE = PATH_GROUP_LOGIN + "/code";
    public static final String PATH_LOGIN_BIND = PATH_GROUP_LOGIN + "/phone/bind";

    //scheme home
    private static final String PATH_GROUP_HOME = "/home";
    public static final String PATH_HOME = PATH_GROUP_HOME + "/main";

    //scheme circle
    private static final String PATH_GROUP_CIRCLE = "/circle";
    public static final String PATH_CIRCLE = PATH_GROUP_CIRCLE + "/list";
    public static final String PATH_CIRCLE_PUBLISH = PATH_GROUP_CIRCLE + "/publish";
    public static final String PATH_CIRCLE_CREATE = PATH_GROUP_CIRCLE + "/create";
    public static final String PATH_CIRCLE_INFO = PATH_GROUP_CIRCLE + "/info";
    public static final String PATH_CIRCLE_OWNER_CIRCLE = PATH_GROUP_CIRCLE + "/owner/circle";
    public static final String PATH_CIRCLE_OPERATION = PATH_GROUP_CIRCLE + "/operation";

    //scheme location
    private static final String PATH_GROUP_LOCATION = "/location";
    public static final String PATH_LOCATION_SELECT = PATH_GROUP_LOCATION + "/select";

    //scheme mall
    private static final String PATH_GROUP_MALL = "/mall";
    public static final String PATH_MALL = PATH_GROUP_MALL + "/home";
    public static final String PATH_GOOD_LIST = PATH_GROUP_MALL + "/good/list";
    public static final String PATH_GOOD_DETAIL = PATH_GROUP_MALL + "/good/detail";
    public static final String PATH_ORDER = PATH_GROUP_MALL + "/order";
    public static final String PATH_ORDER_LIST = PATH_GROUP_MALL + "/order/list";
    public static final String PATH_MALL_SEARCH = PATH_GROUP_MALL + "/search";

    //scheme me
    private static final String PATH_GROUP_ME = "/me";
    public static final String PATH_ME = PATH_GROUP_ME + "/mine";

    //scheme setting
    private static final String PATH_GROUP_SETTING = "/setting";
    public static final String PATH_SETTING = PATH_GROUP_SETTING + "/main";

    //scheme withdraw
    private static final String PATH_GROUP_WITHDRAW = "/withdraw";
    public static final String PATH_WITHDRAW = PATH_GROUP_WITHDRAW + "/main";

    //login interceptor
    public static final String[] LOGIN_REQUIRED_PATH = {PATH_CIRCLE_PUBLISH, PATH_CIRCLE_CREATE,
            PATH_ORDER, PATH_GOOD_DETAIL};

    static {
        supportPath.add(PATH_LOGIN_INTRO);
    }
}

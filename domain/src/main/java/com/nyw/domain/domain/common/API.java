package com.nyw.domain.domain.common;

public class API {
    public static class HomeAPI {
        public static final String login = "user/login";//1登录
        public static final String register = "user/register";//2注册
        public static final String article = "article/list/{page}/json";//1。首页文章 2。知识体系文章
        public static final String article_top = "article/top/json";//置顶文章
        public static final String banner = "banner/json";//banner图
        public static final String hotkey = "hotkey/json";//搜索热词
        public static final String search = "article/query/{page}/json";//搜索
        public static final String tree = "tree/json";//知识体系
        public static final String navi = "navi/json";//导航
        public static final String wxarticle_tab = "wxarticle/chapters/json";//公众号Tab
        public static final String wxarticle_list = "wxarticle/list/{id}/{page}/json";//公众号Tab
        public static final String project_tab = "project/tree/json";//公众号Tab
        public static final String project_list = "project/list/{page}/json";//公众号Tab
        public static final String collect_list = "lg/collect/list/{page}/json";//我的收藏文章列表
        public static final String uncollect_list = "lg/uncollect/{id}/json";//在我的收藏中取消收藏


        public static final String collect_article = "lg/collect/{id}/json";//收藏站内文章
        public static final String collect_outarticle = "lg/collect/add/json";//收藏站外文章
        public static final String uncollect_article = "lg/uncollect_originId/{id}/json";//在列表中取消收藏
    }
}

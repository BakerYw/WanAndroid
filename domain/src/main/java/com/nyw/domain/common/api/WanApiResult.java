package com.nyw.domain.common.api;

import com.bakerj.rxretrohttp.bean.IApiResult;

public class WanApiResult<T> implements IApiResult<T> {


    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * data : {"curPage":2,"datas":[{"apkLink":"","author":"BlackZheng","chapterId":471,"chapterName":"10.0（Q）","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8636,"link":"https://juejin.im/post/5d0b1739e51d4510a73280cc","niceDate":"2019-06-24","origin":"","prefix":"","projectLink":"","publishTime":1561379171000,"superChapterId":453,"superChapterName":"版本适配","tags":[],"title":"关于Android Q分区存储的一些适配心得","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"三好码农","chapterId":313,"chapterName":"字节码","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8635,"link":"https://juejin.im/post/5d0fa403f265da1bb67a2335","niceDate":"2019-06-24","origin":"","prefix":"","projectLink":"","publishTime":1561372474000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"重学Java-一个Java对象到底占多少内存","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>如果不是，那什么场景下不是。<\/p>","envelopePic":"","fresh":false,"id":8626,"link":"https://www.wanandroid.com/wenda/show/8626","niceDate":"2019-06-23","origin":"","prefix":"","projectLink":"","publishTime":1561299498000,"superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 View中的getContext一定返回的是Activity对象吗？","type":0,"userId":2,"visible":1,"zan":39},{"apkLink":"","author":"MarioFeng","chapterId":142,"chapterName":"ConstraintLayout","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8633,"link":"https://juejin.im/post/5d0c46246fb9a07ec9561573","niceDate":"2019-06-23","origin":"","prefix":"","projectLink":"","publishTime":1561271661000,"superChapterId":125,"superChapterName":"5.+高新技术","tags":[],"title":"ConstraintLayout 2.0 新特性详解及实战","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"koller","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8632,"link":"https://www.jianshu.com/p/244d0ba0ee40","niceDate":"2019-06-23","origin":"","prefix":"","projectLink":"","publishTime":1561271631000,"superChapterId":191,"superChapterName":"热门专题","tags":[],"title":"Android优化全面攻略","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"MarioFeng","chapterId":470,"chapterName":"协程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8631,"link":"https://juejin.im/post/5d0afe0bf265da1b7152fb00","niceDate":"2019-06-20","origin":"","prefix":"","projectLink":"","publishTime":1561039088000,"superChapterId":470,"superChapterName":"Kotlin","tags":[],"title":"Kotlin 协程入门这一篇就够了","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"流船","chapterId":176,"chapterName":"个人博客","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8630,"link":"https://www.jianshu.com/p/00fa7045735b","niceDate":"2019-06-20","origin":"","prefix":"","projectLink":"","publishTime":1561038960000,"superChapterId":177,"superChapterName":"推荐网站","tags":[],"title":"Android 你需要的所有进阶资源","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"波澜步惊","chapterId":100,"chapterName":"RecyclerView","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8629,"link":"https://www.jianshu.com/p/40820ea48457","niceDate":"2019-06-20","origin":"","prefix":"","projectLink":"","publishTime":1561038938000,"superChapterId":125,"superChapterName":"5.+高新技术","tags":[],"title":"RecyclerView的回收复用机制解密","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"minminaya","chapterId":99,"chapterName":"具体案例","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8628,"link":"https://www.jianshu.com/p/73a1ee9f376a","niceDate":"2019-06-20","origin":"","prefix":"","projectLink":"","publishTime":1561038914000,"superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"Android通用圆角布局（兼容Android P）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"groovys","chapterId":169,"chapterName":"gradle","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8627,"link":"https://groovys.readthedocs.io/zh/latest/GettingStarted/The-Groovy-Development-Kit.html","niceDate":"2019-06-20","origin":"","prefix":"","projectLink":"","publishTime":1561038321000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":" Groovy 开发","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>相信大家多数都能说出自定义的步骤，但是很多细节上的问题其实也需要关注。<\/p><br><p>针对：<\/p><br><p>MesureSpec.UNSPECIFIED<\/p><br><p>1. 这个模式什么时候会遇到？<\/p><p>2. 遇到后怎么处理？<\/p><p>3. 有什么注意事项？<\/p><br><p>以后问答每周 2-3 问，没有人回答会挂稍微久一些，希望大家踊跃参与。<\/p><br><br><br>","envelopePic":"","fresh":false,"id":8613,"link":"https://www.wanandroid.com/wenda/show/8613","niceDate":"2019-06-20","origin":"","prefix":"","projectLink":"","publishTime":1560961587000,"superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问  详细的描述下自定义 View 测量时 MesureSpec.UNSPECIFIED","type":0,"userId":2,"visible":1,"zan":10},{"apkLink":"","author":"张可_","chapterId":468,"chapterName":"Glide","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8625,"link":"https://www.jianshu.com/p/9bb50924d42a","niceDate":"2019-06-18","origin":"","prefix":"","projectLink":"","publishTime":1560861977000,"superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"Glide 源码分析解读-基于最新版Glide 4.9.0","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"BlackFlagBin","chapterId":468,"chapterName":"Glide","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8624,"link":"https://juejin.im/post/5af95dc66fb9a07aad17a4c3","niceDate":"2019-06-18","origin":"","prefix":"","projectLink":"","publishTime":1560861961000,"superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"Glide源码分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"spiritTalk","chapterId":461,"chapterName":"fresco","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8623,"link":"https://www.jianshu.com/p/5235e44198b2","niceDate":"2019-06-18","origin":"","prefix":"","projectLink":"","publishTime":1560825594000,"superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"fresco 拾遗","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"malihom","chapterId":461,"chapterName":"fresco","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8621,"link":"https://blog.csdn.net/chiefhsing/article/details/53899242","niceDate":"2019-06-17","origin":"","prefix":"","projectLink":"","publishTime":1560779156000,"superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"谈谈fresco的bitmap内存分配","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"GcsSloop","chapterId":126,"chapterName":"绘图相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8620,"link":"https://www.gcssloop.com/customview/Path_Over","niceDate":"2019-06-16","origin":"","prefix":"","projectLink":"","publishTime":1560688514000,"superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"安卓自定义View进阶-Path之完结篇","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"楠之枫雪","chapterId":461,"chapterName":"fresco","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8619,"link":"https://blog.csdn.net/u014614038/article/details/51480072","niceDate":"2019-06-16","origin":"","prefix":"","projectLink":"","publishTime":1560676404000,"superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"Fresco源码赏析 之 基本流程","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"bauerbao","chapterId":252,"chapterName":"奇怪的Bug","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8618,"link":"https://www.jianshu.com/p/378d3def9d14","niceDate":"2019-06-15","origin":"","prefix":"","projectLink":"","publishTime":1560607969000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"Android 虚拟按键隐藏或显示之后共享元素动画异常解决方案","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"MxsQ","chapterId":467,"chapterName":"进程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8617,"link":"https://www.jianshu.com/p/dcff7ac41fd8","niceDate":"2019-06-15","origin":"","prefix":"","projectLink":"","publishTime":1560607703000,"superChapterId":171,"superChapterName":"framework","tags":[],"title":"应用进程是如何创建出来的","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"奔跑的平头哥 ","chapterId":466,"chapterName":"Gson","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8616,"link":"https://www.jianshu.com/p/d04beef7f52a","niceDate":"2019-06-15","origin":"","prefix":"","projectLink":"","publishTime":1560607445000,"superChapterId":461,"superChapterName":"常见开源库源码解析","tags":[],"title":"【Gson源码分析】- 彻底搞懂Gson解析流程","type":0,"userId":-1,"visible":1,"zan":0}],"offset":20,"over":false,"pageCount":335,"size":20,"total":6692}
     * errorCode : 0
     * errorMsg :
     */


    @Override
    public boolean isSuccess() {
        return errorCode==0;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String getResultMsg() {
        return errorMsg;
    }

    @Override
    public String getResultCode() {
        return errorCode+"";
    }

    @Override
    public String getDataField() {
        return "data";
    }



}

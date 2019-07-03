package com.nyw.libproject.common.hybrid.sonic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebView;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BakerJ
 * @date 2018/5/18
 */
public class DeepLinkUtils {
    /**
     * 系统可以处理的url正则
     */
    private static final Pattern ACCEPTED_URI_SCHEME = Pattern.compile("(?i)"
            + // switch on case insensitive matching
            '('
            + // begin group for scheme
            "(?:http|https|ftp|file)://" + "|(?:inline|data|about|javascript):" + "|(?:.*:.*@)"
            + ')' + "(.*)");

    public static boolean shouldOverrideUrlLoadingByApp(WebView view, String url, Activity
            activity) {
        return shouldOverrideUrlLoadingByAppInternal(view, url, true, activity);
    }

    /**
     * 广告业务的特殊处理
     * 根据url的scheme处理跳转第三方app的业务
     * 如果应用程序可以处理该url,就不要让浏览器处理了,返回true;
     * 如果没有应用程序可以处理该url，先判断浏览器能否处理，如果浏览器也不能处理，直接返回false拦截该url，不要再让浏览器处理。
     * 避免出现当deepLink无法调起目标app时，展示加载失败的界面。
     * <p>
     * 某些含有deepLink链接的网页，当使用deepLink跳转目标app失败时，如果将该deepLinkUrl交给系统处理，系统处理不了，会导致加载失败；
     * 示例：
     * 网页Url：https://m.ctrip.com/webapp/hotel/hoteldetail/687592/checkin-1-7.html?allianceid
     * =288562&sid=964106&sourceid=2504&sepopup=12
     * deepLinkUrl：ctrip://wireless/InlandHotel?checkInDate=20170504&checkOutDate=20170505
     * &hotelId=687592&allianceid=288562&sid=960124&sourceid=2504&ouid=Android_Singapore_687592
     *
     * @param interceptExternalProtocol 是否拦截自定义的外部协议，true：拦截，无论如何都不让浏览器处理；false
     *                                  ：不拦截，如何没有成功处理该协议，继续让浏览器处理
     */
    private static boolean shouldOverrideUrlLoadingByAppInternal(WebView view, String url,
                                                                 boolean interceptExternalProtocol, Activity activity) {
        if (isAcceptedScheme(url)) return false;
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        } catch (URISyntaxException e) {
            return interceptExternalProtocol;
        }

        intent.setComponent(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            intent.setSelector(null);
        }

        //intent://dangdang://product//pid=23248697#Intent;
        // scheme=dangdang://product//pid=23248697;package=com.dangdang.buy2;end
        //该Intent无法被设备上的应用程序处理
        if (activity.getPackageManager().resolveActivity(intent, 0) == null) {
            return tryHandleByMarket(intent, activity) || interceptExternalProtocol;
        }

        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            return interceptExternalProtocol;
        }
        return true;
    }

    private static boolean tryHandleByMarket(Intent intent, Activity activity) {
        String packagename = intent.getPackage();
        if (packagename != null) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="
                    + packagename));
            try {
                activity.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * 该url是否属于浏览器能处理的内部协议
     */
    private static boolean isAcceptedScheme(String url) {
        //正则中忽略了大小写，保险起见，还是转成小写
        String lowerCaseUrl = url.toLowerCase();
        Matcher acceptedUrlSchemeMatcher = ACCEPTED_URI_SCHEME.matcher(lowerCaseUrl);
        if (acceptedUrlSchemeMatcher.matches()) {
            return true;
        }
        return false;
    }
}

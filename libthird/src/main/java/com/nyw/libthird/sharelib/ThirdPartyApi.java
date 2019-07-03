package com.nyw.libthird.sharelib;

import android.app.Activity;
import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * @author BakerJ
 * @date 2017/12/29
 */

public class ThirdPartyApi {
    public static final String QQ = "QQ", WECHAT = "weixin", TAOBAO = "taobao";

    public static void login(final Activity context, String thirdParty, final AuthListener
            listener) {
        SHARE_MEDIA shareMedia = null;
        if (QQ.equals(thirdParty)) {
            shareMedia = SHARE_MEDIA.QQ;
        } else if (WECHAT.equals(thirdParty)) {
            shareMedia = SHARE_MEDIA.WEIXIN;
        }
        UMShareAPI.get(context).getPlatformInfo(context, shareMedia, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                listener.onStart(getMediaThirdParty(share_media));
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                listener.onComplete(getMediaThirdParty(share_media), i, getLoginInfoFromMap(map));
                UMShareAPI.get(context).release();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                listener.onError(getMediaThirdParty(share_media), i, throwable);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                listener.onCancel(getMediaThirdParty(share_media), i);
            }
        });
    }

    private static ThirdPartyLoginInfo getLoginInfoFromMap(Map<String, String> map) {
        ThirdPartyLoginInfo info = new ThirdPartyLoginInfo();
        info.setOpenId(map.get("uid"));
        info.setName(map.get("name"));
        info.setGender(map.get("gender"));
        info.setIconUrl(map.get("iconurl"));
        return info;
    }

    private static String getMediaThirdParty(SHARE_MEDIA share_media) {
        if (share_media == SHARE_MEDIA.QQ) {
            return QQ;
        } else if (share_media == SHARE_MEDIA.WEIXIN) {
            return WECHAT;
        }
        return "";
    }

    public static void wxPay(Context context, PayReq request) {
        final IWXAPI wxApi = WXAPIFactory.createWXAPI(context, null);
        wxApi.registerApp("wx0f25933afc3cc9a4");
        wxApi.sendReq(request);
    }

    public interface AuthListener {

        void onStart(String thirdParty);

        void onComplete(String thirdParty, int action, ThirdPartyLoginInfo info);

        void onError(String thirdParty, int action, Throwable throwable);

        void onCancel(String thirdParty, int action);
    }
}

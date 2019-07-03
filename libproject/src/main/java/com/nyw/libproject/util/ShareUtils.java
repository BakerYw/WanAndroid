package com.nyw.libproject.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.nyw.libproject.R;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * @author BakerJ
 * @date 2018/4/19
 */
public class ShareUtils {
    public static final int SHARE_WECHAT_MOMENT = 0, SHARE_WECHAT = 1, SHARE_QQ = 2, SHARE_QZONE
            = 3, COPY_LINK = 5;

    public static void shareArticle(Activity activity, String url, String title, String
            imgUrl, String desc, int resId, OnShareListener onShareListener) {
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet
                .BottomGridSheetBuilder(activity);
        builder.addItem(R.mipmap.share_wechat_moment, activity.getString(R.string
                .share_wechat_moment), SHARE_WECHAT_MOMENT, QMUIBottomSheet
                .BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.share_wechat, activity.getString(R.string.share_wechat),
                        SHARE_WECHAT, QMUIBottomSheet
                                .BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.share_qq, activity.getString(R.string.share_qq), SHARE_QQ,
                        QMUIBottomSheet
                                .BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.share_qzone, activity.getString(R.string.share_qzone),
                        SHARE_QZONE, QMUIBottomSheet
                                .BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.copy_link, activity.getString(R.string.copy_link), COPY_LINK,
                        QMUIBottomSheet
                                .BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener((dialog, itemView) -> {
                    dialog.dismiss();
                    ShareAction shareAction = generateShareArticle(activity, url, title, desc,
                            imgUrl, resId);
                    int tag = (int) itemView.getTag();
                    switch (tag) {
                        case SHARE_WECHAT_MOMENT:
                            shareWechatMoment(shareAction);
                            break;
                        case SHARE_WECHAT:
                            shareWechat(shareAction);
                            break;
                        case SHARE_QQ:
                            shareQQ(shareAction);
                            break;
                        case SHARE_QZONE:
                            shareQZone(shareAction);
                            break;
                        case COPY_LINK:
                            try {
                                copyLink(title, url);
                                onShareListener.onLinkCopy();
                            } catch (Exception ignored) {
                                onShareListener.shareFailed();
                            }
                            return;
                    }
//                    shareAction.setCallback(new UMShareListener() {
//                        @Override
//                        public void onStart(SHARE_MEDIA share_media) {
//
//                        }
//
//                        @Override
//                        public void onResult(SHARE_MEDIA share_media) {
//                            onShareListener.shareSuccess();
//                        }
//
//                        @Override
//                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                            onShareListener.shareFailed();
//                        }
//
//                        @Override
//                        public void onCancel(SHARE_MEDIA share_media) {
//                            onShareListener.shareCanceled();
//                        }
//                    });
                    shareAction.share();
                }).build().show();
    }

    private static ShareAction generateShareArticle(Activity context, String url, String title,
                                                    String desc, String imgUrl, int resId) {
        ShareAction shareAction = new ShareAction(context);
        UMWeb web = new UMWeb(url);
        web.setTitle(title);
        web.setDescription(desc);
        UMImage image = TextUtils.isEmpty(imgUrl) ? new UMImage(context, resId) : new UMImage
                (context, imgUrl);
        web.setThumb(image);
        shareAction.withMedia(web);
        return shareAction;
    }

    private static void shareWechatMoment(ShareAction shareAction) {
        shareAction.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    private static void shareWechat(ShareAction shareAction) {
        shareAction.setPlatform(SHARE_MEDIA.WEIXIN);
    }

    private static void shareQQ(ShareAction shareAction) {
        shareAction.setPlatform(SHARE_MEDIA.QQ);
    }

    private static void shareQZone(ShareAction shareAction) {
        shareAction.setPlatform(SHARE_MEDIA.QZONE);
    }

    private static void copyLink(String title, String url) {
        ClipBoardUtils.copyText("【" + title + "】" + url);
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data, Context
            context) {
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }

    public interface OnShareListener {
        void shareSuccess();

        void shareFailed();

        void shareCanceled();

        void onLinkCopy();
    }
}

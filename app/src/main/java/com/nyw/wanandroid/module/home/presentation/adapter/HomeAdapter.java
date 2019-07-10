package com.nyw.wanandroid.module.home.presentation.adapter;


import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.common.widget.CollectView;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.libwidgets.utils.img.CustomRoundedCornersTransformation;
import com.nyw.libwidgets.utils.img.GlideUtil;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.main.presentation.activity.MainActivity;
import com.nyw.wanandroid.utils.DateUtil;
import com.yalantis.ucrop.view.OverlayView;

import retrofit2.http.HEAD;


public class HomeAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder>{

    public HomeAdapter() {
        super(R.layout.adapter_home);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.tv_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_author,item.getAuthor())
                .setText(R.id.tv_time, item.getNiceDate())
                .setText(R.id.tv_super_chapter_name, item.getSuperChapterName())
                .setText(R.id.tv_chapter_name, item.getChapterName());
        LinearLayout ll_new = helper.getView(R.id.ll_new);
        if (item.isFresh()) {
            ll_new.setVisibility(View.VISIBLE);
        } else {
            ll_new.setVisibility(View.GONE);
        }
        ImageView iv_img = helper.getView(R.id.iv_img);
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            Glide.with(mContext).load(item.getEnvelopePic())
                    .apply(new RequestOptions().transform(new CustomRoundedCornersTransformation
                            (ConvertUtils.dp2px(2), 0)))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into((ImageView) iv_img);
            iv_img.setVisibility(View.VISIBLE);
        } else {
            iv_img.setVisibility(View.GONE);
        }
//        CollectView cv_collect = helper.itemView.findViewById(R.id.cv_collect);
//        if (item.isCollect()) {
//            cv_collect.setChecked(true);
//        } else {
//            cv_collect.setChecked(false);
//        }
        TextView tv_tag = helper.getView(R.id.tv_tag);
        if (item.getTags() != null && item.getTags().size() > 0) {
            tv_tag.setText(item.getTags().get(0).getName());
            tv_tag.setVisibility(View.VISIBLE);
        } else {
            tv_tag.setVisibility(View.GONE);
        }
    }
}

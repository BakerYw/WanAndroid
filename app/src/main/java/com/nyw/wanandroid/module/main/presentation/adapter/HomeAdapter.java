package com.nyw.wanandroid.module.main.presentation.adapter;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.libwidgets.image.PowerImageView;
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
        ImageView imageView= helper.itemView.findViewById(R.id.iv_image);
        helper.setText(R.id.tv_article_title,item.getTitle())
                .setText(R.id.tv_article_author,item.getAuthor())
                .setText(R.id.tv_article_time, DateUtil.getCircleDisplayDate(item.getPublishTime()));
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            Glide.with(mContext).load(item.getEnvelopePic())
                    .apply(new RequestOptions().transform(new CustomRoundedCornersTransformation
                            (ConvertUtils.dp2px(2), 0)))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into((ImageView) imageView);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}

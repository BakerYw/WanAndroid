package com.nyw.wanandroid.module.home.presentation.fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.common.util.cache.SettingCacheUtil;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.bean.response.home.BannerBean;
import com.nyw.domain.domain.bean.response.home.CollectionBean;
import com.nyw.domain.domain.event.setting.SettingChangeEvent;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBaseListPresenterFragment;
import com.nyw.libwidgets.autoscrollviewpager.BGABanner;
import com.nyw.libwidgets.utils.img.CustomRoundedCornersTransformation;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.mvp.HomeContract;
import com.nyw.wanandroid.module.home.mvp.HomePresenter;
import com.nyw.wanandroid.module.home.presentation.adapter.HomeAdapter;
import com.nyw.wanandroid.module.home.presentation.widget.CollectView;
import com.nyw.wanandroid.module.me.presentation.adapter.CollectionAdapter;
import com.nyw.wanandroid.utils.RvAnimUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = PathConstants.PATH_CIRCLE)
public class HomeFragment extends WanBaseListPresenterFragment<HomePresenter, HomeAdapter,
        ArticleBean> implements HomeContract.View{
    @BindView(R.id.circle_btn_search)
    ImageView circle_btn_search;

    BGABanner homeBanner;
    private List<View> mHeaderTopItemViews;
    private List<ArticleBean> mHeaderTopItemBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSettingChangeEvent(SettingChangeEvent event) {
        if (isDetached()) {
            return;
        }
        if (event.isShowTopChanged()) {
            if (SettingCacheUtil.getInstance().isShowTop()) {
                if (mHeaderTopItemViews == null || mHeaderTopItemViews.size() == 0) {
                    mPresenter.getTopArticleBean();
                }
            } else {
                if (mHeaderTopItemViews != null && mHeaderTopItemViews.size() > 0) {
                    for (View view : mHeaderTopItemViews) {
                        mAdapter.removeHeaderView(view);
                    }
                    mHeaderTopItemViews.clear();
                    mHeaderTopItemBeans.clear();
                }
                mHeaderTopItemViews = null;
                mHeaderTopItemBeans = null;
            }
        }
        if (event.isRvAnimChanged()) {
            RvAnimUtils.setAnim(mAdapter, SettingCacheUtil.getInstance().getRvAnim());
        }
    }
    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        setPresenter(new HomePresenter(this));
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        circle_btn_search.setOnClickListener(v -> { Navigation.navigateToSearch(); });
    }

    @Override
    protected void afterInitView() {
        super.afterInitView();
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        View view=LayoutInflater.from(getContext()).inflate(R.layout.frag_home_head, null, false);
        homeBanner= view.findViewById(R.id.home_banner);
        mAdapter.addHeaderView(view);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Navigation.navigateToWeb(mAdapter.getData().get(position).getLink());
            }
        });
        mAdapter.setOnCollectViewClickListener(new HomeAdapter.OnCollectViewClickListener() {
            @Override
            public void onClick(BaseViewHolder helper, CollectView v, int position) {
                if (!v.isChecked()) {
                    mPresenter.Collect(mAdapter.getData().get(position).getId());
                } else {
                    mPresenter.UnCollect(mAdapter.getData().get(position).getId());
                }
            }
        });
        RvAnimUtils.setAnim(mAdapter, SettingCacheUtil.getInstance().getRvAnim());
    }



    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        if (SettingCacheUtil.getInstance().isShowTop()) {
            mPresenter.getTopArticleBean();
        }
        mPresenter.getBanner();
        refresh();
    }
    @Override
    public void TopArticleBeanGet(List<ArticleBean> data) {
        createHeaderTopItem(data);
    }

    @Override
    public void CollectSuccess() {
        ToastUtils.showShort("收藏成功");
    }

    @Override
    public void UnCollectSuccess() {
        ToastUtils.showShort("取消收藏");
    }

    private void createHeaderTopItem(List<ArticleBean> data) {
        mHeaderTopItemBeans = data;
        if (mHeaderTopItemViews != null) {
            for (View view : mHeaderTopItemViews) {
                mAdapter.removeHeaderView(view);
            }
        }
        if (mHeaderTopItemBeans == null || mHeaderTopItemBeans.isEmpty()) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mHeaderTopItemViews = new ArrayList<>();
        for (int i = 0; i < mHeaderTopItemBeans.size(); i++) {
            View view = inflater.inflate(R.layout.adapter_home, mRecycleView, false);
            mHeaderTopItemViews.add(view);
        }
        for (int i = 0; i < mHeaderTopItemViews.size(); i++) {
            View view = mHeaderTopItemViews.get(i);
            ArticleBean bean = mHeaderTopItemBeans.get(i);
            bindHeaderTopItem(view, bean);
            mAdapter.addHeaderView(view);
        }
    }

    private void bindHeaderTopItem(View view, ArticleBean item) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_author = view.findViewById(R.id.tv_author);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_super_chapter_name = view.findViewById(R.id.tv_super_chapter_name);
        TextView tv_chapter_name = view.findViewById(R.id.tv_chapter_name);
        LinearLayout ll_new = view.findViewById(R.id.ll_new);
        TextView tv_new = view.findViewById(R.id.tv_new);
        ImageView iv_img = view.findViewById(R.id.iv_img);
        CollectView cv_collect = view.findViewById(R.id.cv_collect);
        cv_collect.setChecked(item.isCollect()?true:false);
        TextView tv_tag = view.findViewById(R.id.tv_tag);
        tv_title.setText(item.getTitle());
        tv_author.setText(item.getAuthor());
        tv_time.setText(item.getNiceDate());
        tv_super_chapter_name.setText(item.getSuperChapterName());
        tv_chapter_name.setText(item.getChapterName());
        ll_new.setVisibility(View.VISIBLE);
        tv_new.setText("置顶");
        tv_new.setTextColor(getResources().getColor(R.color.text_accent));
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            Glide.with(getContext()).load(item.getEnvelopePic())
                    .apply(new RequestOptions().transform(new CustomRoundedCornersTransformation
                            (ConvertUtils.dp2px(2), 0)))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into((ImageView) iv_img);
            iv_img.setVisibility(View.VISIBLE);
        } else {
            iv_img.setVisibility(View.GONE);
        }
        if (item.getTags() != null && item.getTags().size() > 0) {
            tv_tag.setText(item.getTags().get(0).getName());
            tv_tag.setVisibility(View.VISIBLE);
        } else {
            tv_tag.setVisibility(View.GONE);
        }
        cv_collect.setOnClickListener(new CollectView.OnClickListener() {
            @Override
            public void onClick(CollectView v) {
                if (!v.isChecked()) {
                    mPresenter.Collect(item.getId());
                } else {
                    mPresenter.UnCollect(item.getId());
                }
            }
        });
        view.setOnClickListener(v -> { Navigation.navigateToWeb(item.getLink()); });
    }


    @Override
    public void refreshSucceed(List<ArticleBean> resultList, boolean hasMore) {
        mAdapter.setNewData(resultList);
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.finishRefresh(0, true, !hasMore);
    }

    @Override
    protected HomeAdapter getAdapter() {
        return new HomeAdapter();
    }

    @Override
    public void BannerBeanGet(List<BannerBean> bannerBeans) {
        if (isAdded() && getActivity() != null) {
            homeBanner.setDelegate((banner1, itemView, banner, position) ->
                    Navigation.urlNavigation(bannerBeans.get(position).getUrl()));
            homeBanner.setAdapter((banner12, itemView, banner, position) -> Glide.with
                    (getActivity()).load(bannerBeans.get(position).getImagePath())
                    .apply(new RequestOptions().transform(new CustomRoundedCornersTransformation
                            (ConvertUtils.dp2px(2), 0)))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into((ImageView) itemView));
            homeBanner.setData(bannerBeans, null);
        }
    }



}

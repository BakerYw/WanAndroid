package com.nyw.wanandroid.module.home.presentation.fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nyw.domain.domain.bean.response.home.ArticlesBean;
import com.nyw.domain.domain.bean.response.home.BannerBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBaseListPresenterFragment;
import com.nyw.libwidgets.autoscrollviewpager.BGABanner;
import com.nyw.libwidgets.utils.img.CustomRoundedCornersTransformation;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.mvp.HomeContract;
import com.nyw.wanandroid.module.home.mvp.HomePresenter;
import com.nyw.wanandroid.module.home.presentation.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = PathConstants.PATH_CIRCLE)
public class HomeFragment extends WanBaseListPresenterFragment<HomePresenter, HomeAdapter,
        ArticlesBean> implements HomeContract.View{
    @BindView(R.id.circle_btn_search)
    ImageView circle_btn_search;
    @BindView(R.id.home_banner)
    BGABanner homeBanner;
    private List<View> mHeaderTopItemViews;
    private List<ArticlesBean> mHeaderTopItemBeans;
    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        setPresenter(new HomePresenter(this));
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
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Navigation.navigateToWeb(mAdapter.getData().get(position).getLink());
            }
        });
    }

    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        mPresenter.getBanner();
        mPresenter.getTopArticleBean();
        refresh();
    }
    @Override
    public void TopArticleBeanGet(List<ArticlesBean> data) {
        createHeaderTopItem(data);
    }

    private void createHeaderTopItem(List<ArticlesBean> data) {
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
            ArticlesBean bean = mHeaderTopItemBeans.get(i);
            bindHeaderTopItem(view, bean);
            mAdapter.addHeaderView(view);
        }
    }

    private void bindHeaderTopItem(View view, ArticlesBean item) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_author = view.findViewById(R.id.tv_author);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_super_chapter_name = view.findViewById(R.id.tv_super_chapter_name);
        TextView tv_chapter_name = view.findViewById(R.id.tv_chapter_name);
        LinearLayout ll_new = view.findViewById(R.id.ll_new);
        TextView tv_new = view.findViewById(R.id.tv_new);
        ImageView iv_img = view.findViewById(R.id.iv_img);
        //CollectView cv_collect = view.findViewById(R.id.cv_collect);
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
//        if (item.isCollect()) {
//            cv_collect.setChecked(true);
//        } else {
//            cv_collect.setChecked(false);
//        }
        if (item.getTags() != null && item.getTags().size() > 0) {
            tv_tag.setText(item.getTags().get(0).getName());
            tv_tag.setVisibility(View.VISIBLE);
        } else {
            tv_tag.setVisibility(View.GONE);
        }
//        cv_collect.setOnClickListener(new CollectView.OnClickListener() {
//            @Override
//            public void onClick(CollectView v) {
//                if (!v.isChecked()) {
//                    presenter.collect(item, v);
//                } else {
//                    presenter.uncollect(item, v);
//                }
//            }
//        });
        view.setOnClickListener(v -> { Navigation.navigateToWeb(item.getLink()); });
    }


    @Override
    public void refreshSucceed(List<ArticlesBean> resultList, boolean hasMore) {
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

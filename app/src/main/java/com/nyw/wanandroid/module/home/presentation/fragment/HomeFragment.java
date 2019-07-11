package com.nyw.wanandroid.module.home.presentation.fragment;

import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.nyw.domain.domain.bean.response.home.ArticlesBean;
import com.nyw.domain.domain.bean.response.home.BannerBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBaseListPresenterFragment;
import com.nyw.libwidgets.autoscrollviewpager.BGABanner;
import com.nyw.libwidgets.utils.img.CustomRoundedCornersTransformation;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.home.mvp.homeContract;
import com.nyw.wanandroid.module.home.mvp.homePresenter;
import com.nyw.wanandroid.module.home.presentation.adapter.HomeAdapter;

import java.util.List;

import butterknife.BindView;

@Route(path = PathConstants.PATH_CIRCLE)
public class HomeFragment extends WanBaseListPresenterFragment<homePresenter, HomeAdapter,
        ArticlesBean> implements homeContract.View{
    @BindView(R.id.circle_btn_search)
    ImageView circle_btn_search;
    @BindView(R.id.home_banner)
    BGABanner homeBanner;

    private List<View> mHeaderTopItemViews;

    //List<String> Bannertitle=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void beforeInitView() {
        super.beforeInitView();
        setPresenter(new homePresenter(this));
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void afterInitView() {
        super.afterInitView();
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(true);
        mPresenter.getBanner();
        mPresenter.getTopArticleBean();
    }

    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
        refresh();
    }
    @Override
    public void TopArticleBeanGet(List<ArticlesBean> bannerBeans) {
    }
    @Override
    public void refreshSucceed(List<ArticlesBean> resultList, boolean hasMore) {
        mAdapter.addData(0, resultList);
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
//            for (int i=0;i<bannerBeans.size();i++){
//                Bannertitle.add(bannerBeans.get(i).getTitle());
//            }
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

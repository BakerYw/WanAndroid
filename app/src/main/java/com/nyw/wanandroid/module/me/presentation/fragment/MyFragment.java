package com.nyw.wanandroid.module.me.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bakerj.base.widgets.refresh.CustomRefreshLayout;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.nyw.domain.common.Constants;
import com.nyw.domain.common.util.cache.UserCacheUtil;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.fragment.WanBasePresenterFragment;
import com.nyw.libwidgets.photopicker.CBPhotoPicker;
import com.nyw.libwidgets.utils.img.CustomRoundedCornersTransformation;
import com.nyw.wanandroid.R;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;



@Route(path = PathConstants.PATH_ME)
public class MyFragment extends WanBasePresenterFragment {

    @BindView(R.id.iv_blur)
    ImageView ivBlur;
    @BindView(R.id.civ_user_icon)
    QMUIRadiusImageView civUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.ll_open)
    LinearLayout llOpen;
    @BindView(R.id.ll_about_me)
    LinearLayout llAboutMe;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.refresh_layout)
    CustomRefreshLayout refreshLayout;
    private Unbinder unbinder;
    private RxPermissions mRxPermission;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    private void initView() {
        mRxPermission=new RxPermissions(getActivity());
        refreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                ivBlur.getLayoutParams().height = rlUserInfo.getMeasuredHeight() + offset;
                ivBlur.requestLayout();
            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {
            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {
            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {
            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {
                ivBlur.getLayoutParams().height = rlUserInfo.getMeasuredHeight() - offset;
                ivBlur.requestLayout();
            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {
            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {
            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(getContext()).load(UserCacheUtil.getUserImage())
                .error(R.color.colorAccent)
                .apply(new RequestOptions().transform(new CustomRoundedCornersTransformation
                        (ConvertUtils.dp2px(2), 0)))
                .transition(new DrawableTransitionOptions().crossFade())
                .transform(new BlurTransformation(20, 8)) // 高斯模糊，参数1：模糊度；参数2：图片缩放x倍后再进行模糊
                .into((ImageView) ivBlur);
        tvUserName.setText(UserCacheUtil.getLoginName(getContext()));
        Glide.with(getContext()).load(UserCacheUtil.getUserImage())
                .error(R.mipmap.ic_launcher_round)
                .into(civUserIcon);
    }

    @Override
    protected void lazyLoadOnce() {
        super.lazyLoadOnce();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.civ_user_icon, R.id.ll_collect, R.id.ll_open, R.id.ll_about_me, R.id.ll_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_user_icon:
                CBPhotoPicker.pickPhoto(mRxPermission, getActivity(),1);
                break;
            case R.id.ll_collect:
                Navigation.navigateToCollection();
                break;
            case R.id.ll_open:
                Navigation.navigateToOpen();
                break;
            case R.id.ll_about_me:
                break;
            case R.id.ll_setting:
                Navigation.navigateToSetting();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CBPhotoPicker.onActivityResult(requestCode, resultCode, data, getActivity(), new CBPhotoPicker.OnPhotoPickListener() {
            @Override
            public void getPhoto(String[] photoPaths) {
                Log.e("aaa",photoPaths[0]+"");
                Glide.with(getContext()).load(photoPaths[0]).into(civUserIcon);
                Glide.with(getContext()).load(photoPaths[0])
                        .apply(new RequestOptions().transform(new CustomRoundedCornersTransformation
                                (ConvertUtils.dp2px(2), 0)))
                        .transition(new DrawableTransitionOptions().crossFade())
                        .transform(new BlurTransformation(20, 8)) // 高斯模糊，参数1：模糊度；参数2：图片缩放x倍后再进行模糊
                        .into((ImageView) ivBlur);
                SPUtils.getInstance().put(Constants.USERPIC,photoPaths[0]);
            }
        });
    }
}

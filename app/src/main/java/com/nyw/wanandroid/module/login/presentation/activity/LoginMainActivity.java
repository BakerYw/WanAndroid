package com.nyw.wanandroid.module.login.presentation.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nyw.domain.common.util.cache.UserCacheUtil;
import com.nyw.domain.domain.bean.response.login.LoginBean;
import com.nyw.domain.domain.bean.response.login.LoginInfo;
import com.nyw.domain.domain.event.IInterceptEvent;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.domain.domain.router.RouterInterceptorConstants;
import com.nyw.libproject.common.activity.WanBaseTitleBackActivity;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.login.mvp.loginContract;
import com.nyw.wanandroid.module.login.mvp.loginPresenter;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.State;

@Route(path = PathConstants.PATH_LOGIN_MAIN)
public class LoginMainActivity extends WanBaseTitleBackActivity implements loginContract.View{
    @State
    @Autowired(name = RouterInterceptorConstants.POSTCARD_EVENT)
    IInterceptEvent mEvent;
    @BindView(R.id.login_et_username)
    EditText loginEtUsername;
    @BindView(R.id.login_et_pass)
    EditText loginEtPass;
    @BindView(R.id.login_et_pass2)
    EditText loginEtPass2;
    @BindView(R.id.regist_item)
    LinearLayout registItem;
    @BindView(R.id.login_v_line2)
    View loginVLine2;
    @BindView(R.id.login_btn)
    TextView loginBtn;
    @BindView(R.id.login_tv_contract)
    TextView loginTvContract;
    private loginPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);
        ButterKnife.bind(this);
        inflateBaseView();
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        setTitleTxt("登录");
        mPresenter = new loginPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dismissLoading();
        BarUtils.setStatusBarLightMode(this, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.login_btn, R.id.login_tv_contract})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (loginBtn.getText().equals("登录")){
                    if (loginEtUsername.getText().toString()==null&&loginEtPass.getText().toString()==null){
                        ToastUtils.showLong("用户名或密码不能为空");
                    }else {
                        mPresenter.Login(loginEtUsername.getText().toString(),loginEtPass.getText().toString());
                    }
                }else {
                    if (loginEtUsername.getText().toString()==null&&loginEtPass.getText().toString()==null&&loginEtPass2.getText().toString()==null){
                        ToastUtils.showLong("用户名或密码不能为空");
                    }else if (!loginEtPass.getText().toString().equals(loginEtPass2.getText().toString())) {
                        ToastUtils.showLong("两次密码不一致");
                    }else {
                        mPresenter.Register(loginEtUsername.getText().toString(),loginEtPass.getText().toString(),loginEtPass2.getText().toString());
                    }
                }
                break;
            case R.id.login_tv_contract:
                setTitleTxt("注册");
                loginBtn.setText("注册");
                loginTvContract.setText("注册后自动登录");
                registItem.setVisibility(View.VISIBLE);
                loginVLine2.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void Login(LoginBean data) {
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setLoginName(data.getUsername());
        loginInfo.setLoginPwd(data.getPassword());
        UserCacheUtil.saveLoginInfo(loginInfo);
        finish();
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

    @Override
    public void Register(LoginBean data) {
        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setLoginName(data.getUsername());
        loginInfo.setLoginPwd(data.getPassword());
        UserCacheUtil.saveLoginInfo(loginInfo);        finish();
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
    }

}

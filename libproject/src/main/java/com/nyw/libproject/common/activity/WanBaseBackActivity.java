package com.nyw.libproject.common.activity;

import android.view.View;
import android.widget.ImageView;

import com.nyw.libproject.R;
import com.nyw.libproject.util.ShareUtils;


public class WanBaseBackActivity extends WanBaseActivity implements ShareUtils.OnShareListener {
    protected ImageView commonIvBack;
    protected View commonHeaderRoot;

    protected void inflateBaseView() {
        commonIvBack = findViewById(R.id.common_iv_back);
        commonIvBack.setOnClickListener(view -> back());
        commonHeaderRoot = findViewById(R.id.header_root);
    }

    public void setBackVisibility(int visibility) {
        commonIvBack.setVisibility(visibility);
    }

    void back() {
        onBackPressed();
    }

    public void setHeaderVisibility(int visibility) {
        commonHeaderRoot.setVisibility(visibility);
    }

    @Override
    public void shareSuccess() {
        showToast(getString(R.string.share_success));
    }

    @Override
    public void shareFailed() {
        showToast(getString(R.string.share_failed));
    }

    @Override
    public void shareCanceled() {
        showToast(getString(R.string.share_canceled));
    }

    @Override
    public void onLinkCopy() {
        showToast(getString(R.string.clipboard_copied));
    }
}

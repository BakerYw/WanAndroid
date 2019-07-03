package com.nyw.wanandroid.utils.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.nyw.domain.domain.router.Navigation;

/**
 * @author BakerJ
 * @date 2017/12/30
 * 降级服务，所有无法识别的路径，降级到H5
 */
@Route(path = "/xxx/xxx")
public class DegradeServiceImpl implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        if (postcard.getUri() != null) {
            Navigation.navigateToWeb(postcard.getUri().toString());
        } else if (postcard.getPath() != null) {
            Navigation.navigateToWeb(postcard.getPath());
        }
    }

    @Override
    public void init(Context context) {

    }
}

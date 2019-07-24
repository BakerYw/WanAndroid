package com.nyw.wanandroid.module.knowledge.presentation.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.ArticlesBean;
import com.nyw.domain.domain.bean.response.home.KnowledgeArtBean;
import com.nyw.domain.domain.bean.response.home.KnowledgeNavBean;
import com.nyw.domain.domain.router.Navigation;
import com.nyw.libwidgets.floatlayout.FloatLayout;
import com.nyw.wanandroid.R;

import java.util.LinkedList;
import java.util.Queue;


public class KnowledgeNavAdapter2 extends BaseQuickAdapter<KnowledgeNavBean, BaseViewHolder>{
    private LayoutInflater mInflater = null;
    private Queue<TextView> mFlexItemTextViewCaches = new LinkedList<>();

    public KnowledgeNavAdapter2() {
        super(R.layout.adapter_know_art);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeNavBean item) {
        helper.setText(R.id.tv_name, item.getName());
        FloatLayout fl = helper.getView(R.id.fbl);
        for (int i = 0; i < item.getArticles().size(); i++) {
            ArticlesBean childItem = item.getArticles().get(i);
            TextView child = createOrGetCacheFlexItemTextView(fl);
            child.setText(childItem.getTitle());
            int finalI = i;
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.navigateToWeb(item.getArticles().get(finalI).getLink());
                }
            });
            fl.addView(child);
        }
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        FloatLayout fl = holder.getView(R.id.fbl);
        for (int i = 0; i < fl.getChildCount(); i++) {
            mFlexItemTextViewCaches.offer((TextView) fl.getChildAt(i));
        }
        fl.removeAllViews();
    }

    private TextView createOrGetCacheFlexItemTextView(FloatLayout fbl) {
        TextView tv = mFlexItemTextViewCaches.poll();
        if (tv != null) {
            return tv;
        }
        return createFlexItemTextView(fbl);
    }

    private TextView createFlexItemTextView(FloatLayout fbl) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(fbl.getContext());
        }
        return (TextView) mInflater.inflate(R.layout.rv_item_knowledge_child, fbl, false);
    }

    public interface OnItemClickListener {
        void onClick(KnowledgeNavBean bean, int pos);
    }
}

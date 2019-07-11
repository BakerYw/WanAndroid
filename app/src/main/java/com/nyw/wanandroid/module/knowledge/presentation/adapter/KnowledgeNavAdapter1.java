package com.nyw.wanandroid.module.knowledge.presentation.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.KnowledgeNavBean;
import com.nyw.wanandroid.R;

public class KnowledgeNavAdapter1 extends BaseQuickAdapter<KnowledgeNavBean, BaseViewHolder> {
    // 当前索引
    private int mSelectPosition=0;
    public KnowledgeNavAdapter1() {
        super(R.layout.adapter_know_nav);
    }
    @Override
    protected void convert(BaseViewHolder helper, KnowledgeNavBean item) {
        helper.setText(R.id.tv_name, item.getName());
        if (helper.getAdapterPosition()==mSelectPosition){
            helper.setTextColor(R.id.tv_name,helper.itemView.getResources().getColor(R.color.colorAccent));
        }else {
            helper.setTextColor(R.id.tv_name,helper.itemView.getResources().getColor(R.color.black));
        }
    }
    public void setSelectPos(int position) {
        if (position >= 0 && position < getItemCount() && mSelectPosition != position) {
            mSelectPosition = position;
            notifyDataSetChanged();
        }
    }
}

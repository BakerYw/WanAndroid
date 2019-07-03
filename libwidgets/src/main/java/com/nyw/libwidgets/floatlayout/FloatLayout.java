package com.nyw.libwidgets.floatlayout;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;

import com.qmuiteam.qmui.widget.QMUIFloatLayout;

/**
 * @author BakerJ
 * @date 2018/4/3
 */
public class FloatLayout extends QMUIFloatLayout {
    private BaseAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener;
    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }
    };

    public FloatLayout(Context context) {
        super(context);
    }

    public FloatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(BaseAdapter adapter) {
        this.mAdapter = adapter;
        mAdapter.registerDataSetObserver(mDataSetObserver);
        removeAllViews();
        notifyDataSetChanged();
    }

    private void addAdaptView(final int position) {
        View convertView = getChildAt(position);
        View result = mAdapter.getView(position, convertView, this);
        if (convertView == null) {
            addView(result);
            convertView = result;
        }
        convertView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    private void notifyDataSetChanged() {
        int chilcCount = mAdapter.getCount();
        for (int i = 0; i < chilcCount; i++) {
            addAdaptView(i);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

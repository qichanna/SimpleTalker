package com.liqi.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.liqi.common.R;
import com.liqi.common.widget.recycler.RecyclerAdapter;


public class GalleyView extends RecyclerView {
    private Adapter mAdapter = new Adapter();

    public GalleyView(Context context) {
        super(context);
        init();
    }

    public GalleyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GalleyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(),4));
        setAdapter(mAdapter);
        mAdapter.setListener(new RecyclerAdapter.AdapterListener<Image>() {
            @Override
            public void OnItemClick(RecyclerAdapter.ViewHolder holder, Image image) {

            }

            @Override
            public void onItemLongClick(RecyclerAdapter.ViewHolder holder, Image image) {

            }
        });
    }

    private static class Image{

    }

    private class Adapter extends RecyclerAdapter<Image>{

        @Override
        public void update(Image image, ViewHolder<Image> holder) {

        }

        @Override
        protected int getItemType(int position, Image image) {
            return R.layout.cell_galley;
        }

        @Override
        protected ViewHolder<Image> onCreateViewHolder(View root, int viewType) {
            return new GalleyView.ViewHolder (root);
        }
    }

    private class ViewHolder extends RecyclerAdapter.ViewHolder<Image>{

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Image image) {

        }
    }
}

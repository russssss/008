package com.example.t_008.app.currency_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.t_008.app.CurrencyViewListener;

import java.util.List;

public class CurrencyView extends ViewPager {

    private List<CurrencyViewModel> currencyViewModelList;
    private CurrencyViewAdapter currencyViewAdapter;
    private CurrencyViewListener currencyViewListener;

    private int currentPosition;

    public CurrencyView(@NonNull Context context) {
        super(context);
        init();
    }

    public CurrencyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setData(final List<CurrencyViewModel> currencyViewModelList) {
        this.currencyViewModelList = currencyViewModelList;
        currencyViewAdapter.update(currencyViewModelList);
        currencyViewAdapter.notifyDataSetChanged();

        if (currencyViewModelList.size() > currentPosition) {
            setCurrentItem(currentPosition);
        }
    }

    public void setCurrencyViewListener(CurrencyViewListener currencyViewListener) {
        this.currencyViewListener = currencyViewListener;
    }

    void init() {

        setClipToPadding(false);
        setPadding(40, 0, 40, 0);
        setPageMargin(20);

        currencyViewAdapter = new CurrencyViewAdapter();
        setAdapter(currencyViewAdapter);

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPosition = i;
                if (currencyViewListener != null) {
                    currencyViewListener.onCurrencySelected(currencyViewModelList.get(i));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }

        if (height != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public CurrencyViewModel getCurrency() {
        return currencyViewModelList == null ? null : currencyViewModelList.get(currentPosition);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}

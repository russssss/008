package com.example.t_008.feature_app.currency_view;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.t_008.R;

import java.util.List;

public class CurrencyViewAdapter extends PagerAdapter {

    private List<CurrencyViewModel> currencyViewModelList;

    @Override
    public int getCount() {
        return currencyViewModelList == null ? 0 : currencyViewModelList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(collection.getContext());

        TextView textView = (TextView) inflater.inflate(R.layout.page, collection, false);
        textView.setText(currencyViewModelList.get(position).getDisplayCurrency());

        collection.addView(textView);
        return textView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void update(List<CurrencyViewModel> currencyViewModelList) {
        this.currencyViewModelList = currencyViewModelList;
        notifyDataSetChanged();
    }

}

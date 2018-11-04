package com.example.jyo05.imagepager;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private int currentPage = (-1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
        for (int i = 0; i < 5; ++i) {
            list.add("drawable" + i);
        }
        CustomAdapter adapter = new CustomAdapter(this, list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void prev(View v) {
        if (currentPage > 0) {
            --currentPage;
        }
        viewPager.setCurrentItem(currentPage);
    }

    public void next(View v) {
        if (currentPage < list.size() - 1) {
            ++currentPage;
        }
        viewPager.setCurrentItem(currentPage);
    }
}

class CustomAdapter extends PagerAdapter {

    Context context;
    List<String> list;

    public CustomAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pager_item, null);

        ImageView imageView = view.findViewById(R.id.imageView);
        int resId = context.getResources().getIdentifier(list.get(position), "drawable", context.getPackageName());
        imageView.setImageResource(resId);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}

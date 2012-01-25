package com.github.browep.viewpagergallery;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class Main extends FragmentActivity implements AdapterView.OnItemSelectedListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private Gallery gallery;

    protected Integer[] mImageIds = {
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7
    };
    private TextView textView;
    private Handler handler;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(this);

        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter());
        gallery.setOnItemSelectedListener(this);

        textView = (TextView) findViewById(R.id.title);

        handler = new Handler();

    }

    private void updateUI(final int i){
        handler.post(new Runnable() {
            public void run() {
                gallery.setSelection(i);
                viewPager.setCurrentItem(i);
                textView.setText("Photo #" + i);
            }
        });
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        updateUI(i);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {    }

    public void onPageScrolled(int i, float v, int i1) {    }

    public void onPageSelected(int i) {
        updateUI(i);
    }

    public void onPageScrollStateChanged(int i) {    }

    public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;

        public ImageAdapter() {

        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(Main.this);

            imageView.setImageResource(mImageIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(mGalleryItemBackground);

            return imageView;
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public Fragment getItem(final int position) {
            return new Fragment() {
                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    ImageView imageView = new ImageView(Main.this);
                    imageView.setImageResource(mImageIds[position]);
                    return imageView;
                }
            };
        }
    }


}

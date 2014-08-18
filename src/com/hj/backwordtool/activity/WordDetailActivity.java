package com.hj.backwordtool.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hj.backwordtool.R;
import com.hj.backwordtool.bean.Word;
import com.hj.backwordtool.fragment.WordDetailFragment;
import com.hj.backwordtool.fragment.WordFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/8/12.
 */
public class WordDetailActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPager;

    private ArrayList<Word> words;

    /**
     * 当前所在位置
     */
    private int mCurrentItem = 0;

    private int mMax;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        initTitle();
        words = (ArrayList<Word>) getIntent().getSerializableExtra(WordFragment.EXTRA_DATAS);
        if (words != null)
            mMax = words.size();
        mViewPager = (ViewPager) findViewById(R.id.word_detail_viewpager);
        mViewPager.setAdapter(new MyFragmentPageAdapter(words, getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
                if (mCurrentItem == 0) {
                    findViewById(R.id.pre_word_button).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.pre_word_button).setVisibility(View.VISIBLE);
                }
                if (mCurrentItem == mMax - 1) {
                    findViewById(R.id.next_word_button).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.next_word_button).setVisibility(View.VISIBLE);
                }
            }
        });
        mViewPager.setCurrentItem(mCurrentItem);

        findViewById(R.id.pre_word_button).setOnClickListener(this);
        findViewById(R.id.pre_word_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.next_word_button).setOnClickListener(this);
        if (mMax <= 1) {
            findViewById(R.id.next_word_button).setVisibility(View.INVISIBLE);
        }
    }

    private void initTitle() {
        ImageView imageView = (ImageView) findViewById(R.id.left_title_imageView);
        imageView.setImageResource(R.drawable.chat_title_back_select);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.pre_word_button) {
            if (mCurrentItem > 0) {
                mViewPager.setCurrentItem(mCurrentItem - 1);
            }
        } else {
            if (mCurrentItem < mMax - 1) {
                mViewPager.setCurrentItem(mCurrentItem + 1);
            }
        }
    }

    public class MyFragmentPageAdapter extends FragmentPagerAdapter {
        private ArrayList<Word> words;

        public MyFragmentPageAdapter(ArrayList<Word> words1, FragmentManager fm) {
            super(fm);
            this.words = words1;
        }

        @Override
        public int getCount() {
            if (words != null)
                return words.size();
            return 0;
        }

        @Override
        public Fragment getItem(int position) {
            return WordDetailFragment.newInstance(words.get(position));
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
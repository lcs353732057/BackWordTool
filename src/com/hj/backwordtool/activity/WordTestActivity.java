package com.hj.backwordtool.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.backwordtool.R;
import com.hj.backwordtool.bean.Word;
import com.hj.backwordtool.fragment.WordFragment;
import com.hj.backwordtool.widget.HJRadioGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/8/13.
 */
public class WordTestActivity extends BaseActivity implements View.OnClickListener {
    public static final int SHOW_NEXT_SUBJECT_WHAT = 0;

    public static final int SHOW_RIGHT_PAGE_WHAT = 1;

    private TextView mSubject;

    private TextView mTitleTextView;

    private HJRadioGroup mHJRadioGroup;

    private ArrayList<Subject> mSubjects;

    private int mCurrentPos = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_test);

        mSubjects = initData();

        initTitle();

        mSubject = (TextView) findViewById(R.id.word_title_textview);

        mHJRadioGroup = (HJRadioGroup) findViewById(R.id.subject_radiogroup);

        showAnswer();
    }

    private void initTitle() {
        ImageView imageView = (ImageView) findViewById(R.id.left_title_imageView);
        imageView.setImageResource(R.drawable.chat_title_back_select);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(this);

        mTitleTextView = (TextView) findViewById(R.id.middle_title_textView);
        mTitleTextView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示题目
     */
    private void showAnswer() {
        if (mCurrentPos >= mSubjects.size()) {
            mCurrentPos = 0;
        }
        mTitleTextView.setText("第" + (mCurrentPos + 1) + "关");
        Subject subject = mSubjects.get(mCurrentPos);
        mSubject.setText(subject.word);
        int size = mHJRadioGroup.getChildCount();
        for (int i = 0; i < size; i++) {
            RadioButton radioButton = (RadioButton) mHJRadioGroup.getChildAt(i);
            radioButton.setText(subject.answers[i]);
            radioButton.setChecked(false);
            radioButton.setCompoundDrawables(null, null, null, null);
        }
        mHJRadioGroup.setHasEvent(false);
        mHJRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mHJRadioGroup.setOnCheckedChangeListener(null);
                HJRadioGroup hjRadioGroup = (HJRadioGroup) radioGroup;
                hjRadioGroup.setHasEvent(true);
                //正确答案
                RadioButton radioButtonRight = (RadioButton) radioGroup.getChildAt(0);

                RadioButton radioButtonSelect = (RadioButton) radioGroup.findViewById(i);

                boolean isRight = false;
                if (radioButtonRight != radioButtonSelect) {
                    Drawable drawableRight = getResources().getDrawable(R.drawable.pic_right);
                    drawableRight.setBounds(0, 0, drawableRight.getIntrinsicWidth(), drawableRight.getIntrinsicHeight());
                    radioButtonRight.setCompoundDrawables(null, null, drawableRight, null);

                    Drawable drawableWrong = getResources().getDrawable(R.drawable.pic_wrong);
                    drawableWrong.setBounds(0, 0, drawableWrong.getIntrinsicWidth(), drawableWrong.getIntrinsicHeight());
                    radioButtonSelect.setCompoundDrawables(null, null, drawableWrong, null);
                } else {
                    Drawable drawableRight = getResources().getDrawable(R.drawable.pic_right);
                    drawableRight.setBounds(0, 0, drawableRight.getIntrinsicWidth(), drawableRight.getIntrinsicHeight());
                    radioButtonRight.setCompoundDrawables(null, null, drawableRight, null);
                    isRight = true;
                }

                if (!isRight) {
                    handler.sendEmptyMessageDelayed(SHOW_RIGHT_PAGE_WHAT, 1000);
                }
                handler.sendEmptyMessageDelayed(SHOW_NEXT_SUBJECT_WHAT, 1500);
            }
        });
    }

    private void enterWordDetailPage() {
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(mSubjects.get(mCurrentPos).word, false));
        Intent intent = new Intent(this, WordDetailActivity.class);
        intent.putExtra(WordFragment.EXTRA_DATAS, words);
        startActivity(intent);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NEXT_SUBJECT_WHAT:
                    mCurrentPos++;
                    showAnswer();
                    break;
                case SHOW_RIGHT_PAGE_WHAT:
                    enterWordDetailPage();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        finish();
    }

    private ArrayList<Subject> initData() {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        for (int i = 0; i < 10; i++) {
            Subject subject = new Subject();
            subject.word = "good bye--" + (i + 1);
            subject.answers = new String[]{"n.标志;符号;痕迹", "adv.永远;符号;总是", "abbr.词典，字典", "adj.害羞的"};
            subject.rightPos = 0;
            subjects.add(subject);
        }
        return subjects;
    }

    public class Subject {
        public String word;

        public String[] answers;

        public int rightPos;
    }
}
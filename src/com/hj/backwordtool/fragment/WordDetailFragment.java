package com.hj.backwordtool.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hj.backwordtool.R;
import com.hj.backwordtool.bean.Word;

/**
 * Created by Administrator on 2014/8/12.
 */
public class WordDetailFragment extends BaseFragment {
    public static final String EXTRA = "extra";

    private Word word;

    public static WordDetailFragment newInstance(Word word) {
        WordDetailFragment f = new WordDetailFragment();
        Bundle b = new Bundle();
        b.putSerializable(EXTRA, word);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        word = (Word) bundle.getSerializable(EXTRA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log.d("onCreateView...");
        View view = inflater.inflate(R.layout.fragment_word_detail, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(word.word);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log.d("onActivityCreated...");
    }
}

package com.hj.backwordtool.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.backwordtool.R;
import com.hj.backwordtool.activity.MainActivity;
import com.hj.backwordtool.activity.WordDetailActivity;
import com.hj.backwordtool.activity.WordTestActivity;
import com.hj.backwordtool.adapter.WordAdapter;
import com.hj.backwordtool.bean.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/8/12.
 */
public class WordFragment extends BaseFragment implements View.OnClickListener {
    public static final String EXTRA_DATAS = "extra_datas";

    private ListView mListView;

    private ArrayList<Word> items;

    public static WordFragment newInstance() {
        return new WordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word, null);
    }

    private void init() {
        items = new ArrayList<Word>();
        for (int i = 0; i < 20; i++) {
            Word word = new Word("good bye--" + (i + 1), false);
            items.add(word);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (ListView) getActivity().findViewById(R.id.listView);

        mListView.setAdapter(new WordAdapter(getActivity(), items));
        mListView.setItemsCanFocus(false);
        getActivity().findViewById(R.id.start_study_button).setOnClickListener(this);
        getActivity().findViewById(R.id.start_test_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (R.id.start_study_button == view.getId()) {
            ArrayList<Word> words = findSelectWords();
            if (words.size() > 0) {
                Intent intent = new Intent(getActivity(), WordDetailActivity.class);
                intent.putExtra(EXTRA_DATAS, words);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "请选择单词", Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent(getActivity(), WordTestActivity.class);
            startActivity(intent);
        }
    }

    private ArrayList<Word> findSelectWords() {
        ArrayList<Word> words = new ArrayList<Word>();
        for (Word word : items) {
            if (word.isSelect)
                words.add(word);
        }
        return words;
    }
}

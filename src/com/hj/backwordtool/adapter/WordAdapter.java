package com.hj.backwordtool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hj.backwordtool.R;
import com.hj.backwordtool.bean.Word;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/8/13.
 */
public class WordAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private ArrayList<Word> words;

    public WordAdapter(Context cxt, ArrayList<Word> words) {
        this.words = words;
        inflater = LayoutInflater.from(cxt);
    }

    @Override
    public int getCount() {
        if (words != null)
            return words.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return words.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = inflater.inflate(R.layout.word_item_layout, null);
            holder.textView = (TextView) view.findViewById(R.id.word_item_textView);
            holder.checkBox = (CheckBox) view.findViewById(R.id.word_item_checkBox);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        Word word = words.get(i);
        if (word != null) {
            holder.textView.setText(word.word);
            holder.checkBox.setChecked(word.isSelect);
            holder.checkBox.setTag(word);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Word word1 = (Word) compoundButton.getTag();
                    word1.isSelect = b;
                }
            });
        }
        return view;
    }

    class Holder {
        TextView textView;
        CheckBox checkBox;
    }
}

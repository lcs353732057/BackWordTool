package com.hj.backwordtool.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/8/13.
 */
public class Word implements Serializable {
    public String word;

    public boolean isSelect;

    /**
     * 词义
     */
    public String mean;

    /**
     * 例句
     */
    public String exampleSentence;

    /**
     * 声音路径
     */
    public String voicePath;

    public Word(String word, boolean isSelect) {
        this.word = word;
        this.isSelect = isSelect;
    }
}

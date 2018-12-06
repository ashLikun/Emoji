package com.ashlikun.emoji.listener;

import android.view.View;
import android.view.ViewGroup;

import com.ashlikun.emoji.data.PageEntity;


public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}

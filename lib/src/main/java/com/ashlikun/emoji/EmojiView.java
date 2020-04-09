package com.ashlikun.emoji;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ashlikun.emoji.adapter.PageSetAdapter;
import com.ashlikun.emoji.data.PageSetEntity;
import com.ashlikun.emoji.widget.AutoHeightLayout;
import com.ashlikun.emoji.widget.EmoticonsFuncView;
import com.ashlikun.emoji.widget.EmoticonsIndicatorView;
import com.ashlikun.emoji.widget.EmoticonsToolBarView;

import java.util.ArrayList;


public class EmojiView extends AutoHeightLayout implements EmoticonsFuncView.OnEmoticonsPageViewListener,
        EmoticonsToolBarView.OnToolBarItemClickListener {

    private EmoticonsFuncView mEmoticonsFuncView;
    private EmoticonsIndicatorView mEmoticonsIndicatorView;
    private EmoticonsToolBarView mEmoticonsToolBarView;

    public EmojiView(Context context) {
        super(context, null);
        init(context, null);
    }

    public EmojiView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public void onSoftKeyboardHeightChanged(int i) {

    }

    public EmojiView(@NonNull Context context, @Nullable AttributeSet attrs,
                     @AttrRes int defStyleAttr) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.layout_chatinput_emoji, this);
        mContext = context;

        mEmoticonsFuncView = findViewById(R.id.view_epv);
        mEmoticonsIndicatorView = findViewById(R.id.view_eiv);
        mEmoticonsToolBarView = findViewById(R.id.view_etv);
        mEmoticonsFuncView.setOnIndicatorListener(this);
        mEmoticonsToolBarView.setOnToolBarItemClickListener(this);
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        if (pageSetAdapter != null) {
            ArrayList<PageSetEntity> pageSetEntities = pageSetAdapter.getPageSetEntityList();
            if (pageSetEntities != null) {
                for (PageSetEntity pageSetEntity : pageSetEntities) {
                    mEmoticonsToolBarView.addToolItemView(pageSetEntity);
                }
            }
        }
        mEmoticonsFuncView.setAdapter(pageSetAdapter);
    }

    public void hintEmoticonsToolBarView() {
        findViewById(R.id.view_spit).setVisibility(GONE);
        mEmoticonsToolBarView.setVisibility(GONE);
    }

    @Override
    public void emoticonSetChanged(PageSetEntity pageSetEntity) {
        mEmoticonsToolBarView.setToolBtnSelect(pageSetEntity.getUuid());
    }

    @Override
    public void playTo(int position, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playTo(position, pageSetEntity);
    }

    @Override
    public void playBy(int oldPosition, int newPosition, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playBy(oldPosition, newPosition, pageSetEntity);
    }

    @Override
    public void onToolBarItemClick(PageSetEntity pageSetEntity) {
        mEmoticonsFuncView.setCurrentPageSet(pageSetEntity);
    }

    public EmoticonsFuncView getEmoticonsFuncView() {
        return this.mEmoticonsFuncView;
    }
}

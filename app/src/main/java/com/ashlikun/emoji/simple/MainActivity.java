package com.ashlikun.emoji.simple;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ashlikun.emoji.DefEmoticons;
import com.ashlikun.emoji.EmojiBean;
import com.ashlikun.emoji.EmojiCommonUtils;
import com.ashlikun.emoji.EmojiView;
import com.ashlikun.emoji.adapter.PageSetAdapter;
import com.ashlikun.emoji.listener.EmoticonClickListener;
import com.ashlikun.emoji.listener.SimpleEmoticonClickListener;
import com.ashlikun.emoji.widget.EmoticonsEditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements EmoticonClickListener<EmojiBean> {


    EmojiView emojiView;
    EmoticonsEditText emojiEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emojiView = findViewById(R.id.emojiView);
        emojiEdit = findViewById(R.id.emojiEdit);
        EmojiCommonUtils.initEmoticonsEditText(emojiEdit);
        initView();
    }

    private void initView() {
        PageSetAdapter pageSetAdapter = new PageSetAdapter();
        EmojiCommonUtils.addEmojiPageSetEntity(pageSetAdapter, Arrays.asList(DefEmoticons.sEmojiArray), new SimpleEmoticonClickListener(emojiEdit));
        emojiView.hintEmoticonsToolBarView();
        emojiView.setAdapter(pageSetAdapter);
    }

    @Override
    public void onEmoticonClick(EmojiBean o, int actionType, boolean isDelBtn) {
    }

}

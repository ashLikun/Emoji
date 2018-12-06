package com.ashlikun.emoji.listener;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;

import com.ashlikun.emoji.Constants;
import com.ashlikun.emoji.EmojiBean;
import com.ashlikun.emoji.EmojiCommonUtils;
import com.ashlikun.emoji.data.EmoticonEntity;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/12/6　13:35
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */
public class SimpleEmoticonClickListener implements EmoticonClickListener {
    private EditText mChatInput;

    public SimpleEmoticonClickListener(EditText mChatInput) {
        this.mChatInput = mChatInput;
    }

    @Override
    public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {
        if (isDelBtn) {
            EmojiCommonUtils.delClick(mChatInput);
        } else {
            if (o == null) {
                return;
            }
            if (actionType == Constants.EMOTICON_CLICK_BIGIMAGE) {
                // if(o instanceof EmoticonEntity){
                // OnSendImage(((EmoticonEntity)o).getIconUri());
                // }
            } else {
                String content = null;
                if (o instanceof EmojiBean) {
                    content = ((EmojiBean) o).emoji;
                } else if (o instanceof EmoticonEntity) {
                    content = ((EmoticonEntity) o).getContent();
                }

                if (TextUtils.isEmpty(content)) {
                    return;
                }
                int index = mChatInput.getSelectionStart();
                Editable editable = mChatInput.getText();
                editable.insert(index, content);
            }
        }
    }
}

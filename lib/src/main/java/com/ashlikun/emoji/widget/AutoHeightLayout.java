package com.ashlikun.emoji.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ashlikun.emoji.EmoticonsKeyboardUtils;
import com.ashlikun.emoji.R;


public abstract class AutoHeightLayout extends SoftKeyboardSizeWatchLayout implements SoftKeyboardSizeWatchLayout.OnResizeListener {
    protected Context mContext;
    protected int mMaxParentHeight;
    protected int mSoftKeyboardHeight;
    protected boolean mConfigurationChangedFlag = false;

    public AutoHeightLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mSoftKeyboardHeight = EmoticonsKeyboardUtils.getDefKeyboardHeight(mContext);
        addOnResizeListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        onSoftKeyboardHeightChanged(mSoftKeyboardHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mMaxParentHeight == 0) {
            mMaxParentHeight = h;
        }
    }

    public void updateMaxParentHeight(int maxParentHeight) {
        this.mMaxParentHeight = maxParentHeight;
        if (maxParentHeightChangeListener != null) {
            maxParentHeightChangeListener.onMaxParentHeightChange(maxParentHeight);
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mConfigurationChangedFlag = true;
        mScreenHeight = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mConfigurationChangedFlag) {
            mConfigurationChangedFlag = false;
            Rect r = new Rect();
            ((Activity) mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
            if (mScreenHeight == 0) {
                mScreenHeight = r.bottom;
            }
            int mNowh = mScreenHeight - r.bottom;
            mMaxParentHeight = mNowh;
        }

        if (mMaxParentHeight != 0) {
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int expandSpec = MeasureSpec.makeMeasureSpec(mMaxParentHeight, heightMode);
            super.onMeasure(widthMeasureSpec, expandSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onSoftPop(final int height) {
        if (mSoftKeyboardHeight != height) {
            mSoftKeyboardHeight = height;
            EmoticonsKeyboardUtils.setDefKeyboardHeight(mContext, mSoftKeyboardHeight);
            onSoftKeyboardHeightChanged(mSoftKeyboardHeight);
        }
    }

    @Override
    public void onSoftClose() {
    }

    public abstract void onSoftKeyboardHeightChanged(int height);

    private OnMaxParentHeightChangeListener maxParentHeightChangeListener;

    public interface OnMaxParentHeightChangeListener {
        void onMaxParentHeightChange(int height);
    }

    public void setOnMaxParentHeightChangeListener(OnMaxParentHeightChangeListener listener) {
        this.maxParentHeightChangeListener = listener;
    }
}
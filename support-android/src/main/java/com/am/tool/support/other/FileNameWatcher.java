package com.am.tool.support.other;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * 文件名观察者
 * Created by Alex on 2023/9/20.
 */
public class FileNameWatcher implements TextWatcher {

    private final EditText mTarget;
    private final String mExtension;
    private boolean mIgnore;
    private boolean mHandleOnlyFocused = true;
    private int mIllegalCharacterRes;
    private String mIllegalCharacterStr;
    private int mLengthOverRes;
    private String mLengthOverStr;

    public FileNameWatcher(EditText target, @Nullable String extension) {
        mTarget = target;
        mExtension = extension;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mIgnore) {
            return;
        }
        mIgnore = true;
        if (mHandleOnlyFocused && !mTarget.isFocused()) {
            // 未聚焦时不检查
            mIgnore = false;
            return;
        }
        if (TextUtils.isEmpty(s)) {
            // 文件名为空
            mIgnore = false;
            return;
        }
        String name = s.toString();
        // 删除非法字符
        int index = getFirstIllegalCharacter(name);
        boolean hasIllegalCharacter = false;
        while (index >= 0) {
            hasIllegalCharacter = true;
            s.delete(index, index + 1);
            name = s.toString();
            index = getFirstIllegalCharacter(name);
        }
        if (hasIllegalCharacter) {
            if (mIllegalCharacterRes != 0) {
                Toast.makeText(mTarget.getContext(), mIllegalCharacterRes,
                        Toast.LENGTH_SHORT).show();
            } else if (mIllegalCharacterStr != null) {
                Toast.makeText(mTarget.getContext(), mIllegalCharacterStr,
                        Toast.LENGTH_SHORT).show();
            }
        }
        // 检查长度
        boolean lengthOver = false;
        if (TextUtils.isEmpty(mExtension)) {
            // 无拓展名
            while (s.length() > 255) {
                lengthOver = true;
                int start = mTarget.getSelectionStart();
                if (start == 0) {
                    s.delete(0, 1);
                } else {
                    s.delete(start - 1, start);
                }
            }
        } else {
            // 先处理拓展名
            final int extensionLength = mExtension.length();
            int length = s.length();
            final int indexExtension = length - extensionLength - 1;
            final CharSequence e = "." + mExtension;
            if (indexExtension < 0) {
                final int selectionStart = mTarget.getSelectionStart();
                final int selectionEnd = mTarget.getSelectionEnd();
                s.append(e);
                length = s.length();
                if (selectionStart == selectionEnd) {
                    mTarget.setSelection(selectionStart);
                } else {
                    mTarget.setSelection(length - extensionLength - 1);
                }
            } else {
                final String extension = name.substring(indexExtension, length).toLowerCase();
                if (!TextUtils.equals(extension, "." + mExtension)) {
                    // 追加拓展名
                    final int selectionStart = mTarget.getSelectionStart();
                    final int selectionEnd = mTarget.getSelectionEnd();
                    s.append(e);
                    length = s.length();
                    if (selectionStart == selectionEnd) {
                        mTarget.setSelection(selectionStart);
                    } else {
                        mTarget.setSelection(length - extensionLength - 1);
                    }
                }
            }
            while (length > 255) {
                lengthOver = true;
                final int start = Math.min(mTarget.getSelectionStart(),
                        length - extensionLength - 1);
                if (start == 0) {
                    s.delete(0, 1);
                } else {
                    s.delete(start - 1, start);
                }
                length = s.length();
            }
        }
        if (lengthOver) {
            if (mLengthOverRes != 0) {
                Toast.makeText(mTarget.getContext(), mLengthOverRes,
                        Toast.LENGTH_SHORT).show();
            } else if (mLengthOverStr != null) {
                Toast.makeText(mTarget.getContext(), mLengthOverStr,
                        Toast.LENGTH_SHORT).show();
            }
        }
        mIgnore = false;
    }

    private int getFirstIllegalCharacter(String text) {
        int index = text.indexOf('\u0000');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('\\');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('/');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf(':');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('*');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('?');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('"');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('<');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('>');
        if (index >= 0) {
            return index;
        }
        index = text.indexOf('|');
        if (index >= 0) {
            return index;
        }
        return -1;
    }

    /**
     * 判断是否为聚焦时才处理变更
     *
     * @return 仅聚焦时处理返回true
     */
    public boolean isHandleOnlyFocused() {
        return mHandleOnlyFocused;
    }

    /**
     * 设置是否开启为聚焦时才处理变更
     *
     * @param enable 是否开启
     * @return 自身
     */
    public FileNameWatcher setHandleOnlyFocused(boolean enable) {
        mHandleOnlyFocused = enable;
        return this;
    }

    /**
     * 设置非法字符提示消息
     *
     * @param resId 消息资源
     * @return 自身
     */
    public FileNameWatcher setIllegalCharacterMessage(@StringRes int resId) {
        mIllegalCharacterRes = resId;
        mIllegalCharacterStr = null;
        return this;
    }

    /**
     * 设置非法字符提示消息
     *
     * @param message 消息
     * @return 自身
     */
    public FileNameWatcher setIllegalCharacterMessage(String message) {
        mIllegalCharacterRes = 0;
        mIllegalCharacterStr = message;
        return this;
    }

    /**
     * 设置长度超限提示
     *
     * @param resId 消息资源
     * @return 自身
     */
    public FileNameWatcher setLengthOverMessage(@StringRes int resId) {
        mLengthOverRes = resId;
        mLengthOverStr = null;
        return this;
    }

    /**
     * 设置长度超限提示
     *
     * @param message 消息
     * @return 自身
     */
    public FileNameWatcher setLengthOverMessage(String message) {
        mLengthOverRes = 0;
        mLengthOverStr = message;
        return this;
    }
}

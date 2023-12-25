/*
 * Copyright (C) 2023 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.tool.support.other;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

/**
 * 字符串资源
 * Created by Alex on 2023/11/28.
 */
public class StringResource {
    private final int mRes;
    private final String mStr;

    public StringResource(@StringRes int res) {
        mRes = res;
        mStr = null;
    }

    public StringResource(String str) {
        mRes = ResourcesCompat.ID_NULL;
        mStr = str;
    }

    /**
     * 设置文本
     *
     * @param view     TextView
     * @param resource 字符串资源
     */
    public static void setText(TextView view, StringResource resource) {
        if (view == null) {
            return;
        }
        if (resource == null) {
            view.setText(null);
            return;
        }
        if (resource.mRes != ResourcesCompat.ID_NULL) {
            view.setText(resource.mRes);
        } else {
            view.setText(resource.mStr);
        }
    }

    /**
     * 获取字符串
     *
     * @param context Context
     * @return 字符串
     */
    @Nullable
    public String getString(Context context) {
        if (mRes != ResourcesCompat.ID_NULL) {
            return context.getString(mRes);
        }
        return mStr;
    }
}

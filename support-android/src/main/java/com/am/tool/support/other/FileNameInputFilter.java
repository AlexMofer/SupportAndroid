/*
 * Copyright (C) 2015 AlexMofer
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

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名特殊字符筛选器
 * Created by Alex on 2017/9/8.
 */

public class FileNameInputFilter implements InputFilter {

    private static final String REGEX = ".*[\\?:\"\\*\\|/\\\\<>]+.*";
    private final Pattern mPattern = Pattern.compile(REGEX);

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                               int dstart, int dend) {
        Matcher matcher = mPattern.matcher(source);
        if (matcher.matches()) {
            return matcher.replaceAll("");
        }
        return null;
    }
}

/*
 * Copyright (C) 2022 AlexMofer
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

import java.io.File;
import java.io.FileFilter;

/**
 * 是否存在筛选器
 * Created by Alex on 2022/5/2.
 */
public class ExistFileFilter implements FileFilter {

    private final boolean mCheckExists;

    public ExistFileFilter(boolean checkExists) {
        mCheckExists = checkExists;
    }

    @Override
    public boolean accept(File file) {
        if (mCheckExists) {
            return file.exists();
        }
        return true;
    }
}
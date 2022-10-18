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
package com.am.tool.support.utils;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Uri 工具
 * Created by Alex on 2022/7/22.
 */
public class UriUtils {

    /**
     * 复制
     *
     * @param context Context
     * @param source  源文件
     * @param target  目标文件
     * @return 复制成功时返回true
     */
    public static boolean copy(Context context, Uri source, Uri target) {
        try (final ParcelFileDescriptor.AutoCloseInputStream input =
                     new ParcelFileDescriptor.AutoCloseInputStream(
                             context.getContentResolver()
                                     .openFileDescriptor(source, "r"));
             final ParcelFileDescriptor.AutoCloseOutputStream output =
                     new ParcelFileDescriptor.AutoCloseOutputStream(
                             context.getContentResolver()
                                     .openFileDescriptor(target, "w"))) {
            StreamUtils.copy(input, output);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * 复制
     *
     * @param context Context
     * @param source  源文件
     * @param target  目标文件
     * @return 复制成功时返回true
     */
    public static boolean copy(Context context, Uri source, File target) {
        try (final ParcelFileDescriptor.AutoCloseInputStream input =
                     new ParcelFileDescriptor.AutoCloseInputStream(
                             context.getContentResolver()
                                     .openFileDescriptor(source, "r"));
             final FileOutputStream output = new FileOutputStream(target)) {
            StreamUtils.copy(input, output);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * 复制
     *
     * @param context Context
     * @param source  源文件
     * @param target  目标文件
     * @return 复制成功时返回true
     */
    public static boolean copy(Context context, File source, Uri target) {
        try (final FileInputStream input = new FileInputStream(source);
             final ParcelFileDescriptor.AutoCloseOutputStream output =
                     new ParcelFileDescriptor.AutoCloseOutputStream(
                             context.getContentResolver()
                                     .openFileDescriptor(target, "w"))) {
            StreamUtils.copy(input, output);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
}

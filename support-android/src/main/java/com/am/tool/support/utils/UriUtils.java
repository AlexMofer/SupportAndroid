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
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

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

    @SuppressWarnings("SameParameterValue")
    @Nullable
    private static String queryForString(Context context, Uri self, String column,
                                         @Nullable String defaultValue) {
        try (final Cursor cursor = context.getContentResolver().query(
                self, new String[]{column}, null, null, null)) {
            if (cursor.moveToFirst() && !cursor.isNull(0)) {
                return cursor.getString(0);
            } else {
                return defaultValue;
            }
        } catch (Throwable t) {
            return defaultValue;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static long queryForLong(Context context, Uri self, String column,
                                     long defaultValue) {
        try (final Cursor cursor = context.getContentResolver().query(
                self, new String[]{column}, null, null, null)) {
            if (cursor.moveToFirst() && !cursor.isNull(0)) {
                return cursor.getLong(0);
            } else {
                return defaultValue;
            }
        } catch (Throwable t) {
            return defaultValue;
        }
    }

    /**
     * 获取名称
     *
     * @param context Context
     * @param uri     链接
     * @return 名称
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    public static String getName(Context context, Uri uri) {
        return queryForString(context, uri, DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                null);
    }

    /**
     * 获取名称
     *
     * @param uri 链接
     * @return 名称
     */
    @Nullable
    public static String getNameByPath(Uri uri) {
        final List<String> segments = uri.getPathSegments();
        return (segments == null || segments.isEmpty()) ?
                null : segments.get(segments.size() - 1);
    }

    /**
     * 获取最后编辑时间
     *
     * @param context Context
     * @param uri     链接
     * @return 最后编辑时间
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static long lastModified(Context context, Uri uri) {
        return queryForLong(context, uri, DocumentsContract.Document.COLUMN_LAST_MODIFIED, 0);
    }

    /**
     * 获取文件长度
     *
     * @param context Context
     * @param uri     链接
     * @return 文件长度
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static long length(Context context, Uri uri) {
        return queryForLong(context, uri, DocumentsContract.Document.COLUMN_SIZE, 0);
    }
}

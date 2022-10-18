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

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 序列化工具
 * Created by Alex on 2022/8/25.
 */
public class ParcelableUtils {

    private ParcelableUtils() {
        //no instance
    }

    /**
     * 写入泛形
     *
     * @param dest       目标
     * @param flags      标记
     * @param parcelable 泛形
     */
    public static void writeGenerics(Parcel dest, int flags, Parcelable parcelable) {
        dest.writeString(parcelable.getClass().getName());
        dest.writeParcelable(parcelable, flags);
    }

    /**
     * 读取泛形
     *
     * @param in  数据源
     * @param <T> 类型
     * @return 泛形
     */
    public static <T extends Parcelable> T readGenerics(Parcel in) {
        final String name = in.readString();
        try {
            final Class<?> clazz = Class.forName(name);
            if (Build.VERSION.SDK_INT >= 33) {
                //noinspection unchecked
                return (T) in.readParcelable(clazz.getClassLoader(), clazz);
            } else {
                return in.readParcelable(clazz.getClassLoader());
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
}

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

import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 序列化工具
 * Created by Alex on 2022/8/25.
 */
public class ParcelableUtils {

    private static final String SUFFIX = "_parcelable_utils_class_name";

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
    public static void writeGenerics(@NonNull Parcel dest, int flags,
                                     @Nullable Parcelable parcelable) {
        if (parcelable == null) {
            dest.writeString(null);
        } else {
            dest.writeString(parcelable.getClass().getName());
            dest.writeParcelable(parcelable, flags);
        }
    }

    /**
     * 读取泛形
     *
     * @param in  数据源
     * @param <T> 类型
     * @return 泛形
     */
    public static <T> T readGenerics(@NonNull Parcel in) {
        final String className = in.readString();
        if (className == null) {
            return null;
        }
        try {
            final Class<?> clazz = Class.forName(className);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                //noinspection unchecked
                return (T) in.readParcelable(clazz.getClassLoader(), clazz);
            } else {
                //noinspection unchecked
                return in.readParcelable(clazz.getClassLoader());
            }
        } catch (ClassNotFoundException e) {
            throw new BadParcelableException(e.getMessage());
        }
    }

    /**
     * Inserts a Parcelable value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Parcelable object, or null
     */
    public static void putGenerics(@NonNull Bundle bundle,
                                   @Nullable String key,
                                   @Nullable Parcelable value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (value != null) {
                bundle.putString(key + SUFFIX, value.getClass().getName());
            }
            bundle.putParcelable(key, value);
        } else {
            bundle.putParcelable(key, value);
        }
    }

    /**
     * Returns the value associated with the given key or {@code null} if:
     * <ul>
     *     <li>No mapping of the desired type exists for the given key.
     *     <li>A {@code null} value is explicitly associated with the key.
     *     <li>The object is not of type {@code clazz}.
     * </ul>
     *
     * @param key a String, or {@code null}
     * @return a Parcelable value, or {@code null}
     */
    @Nullable
    public static <T> T getGenerics(@NonNull Bundle bundle, @Nullable String key) {
        final String className = bundle.getString(key + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                final Class<?> clazz = Class.forName(className);
                bundle.setClassLoader(clazz.getClassLoader());
                //noinspection unchecked
                return (T) bundle.getParcelable(key, clazz);
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return bundle.getParcelable(key);
        }
    }

    /**
     * Inserts an array of Parcelable values into the mapping of this Bundle,
     * replacing any existing value for the given key.  Either key or value may
     * be null.
     *
     * @param key   a String, or null
     * @param value an array of Parcelable objects, or null
     */
    public static void putGenericsArray(@NonNull Bundle bundle,
                                        @Nullable String key,
                                        @Nullable Parcelable[] value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String className = null;
            if (value != null) {
                for (Parcelable parcelable : value) {
                    if (parcelable != null) {
                        className = parcelable.getClass().getName();
                        break;
                    }
                }
            }
            if (className != null) {
                bundle.putString(key + SUFFIX, className);
            }
            bundle.putParcelableArray(key, value);
        } else {
            bundle.putParcelableArray(key, value);
        }
    }

    /**
     * Returns the value associated with the given key, or {@code null} if:
     * <ul>
     *     <li>No mapping of the desired type exists for the given key.
     *     <li>A {@code null} value is explicitly associated with the key.
     *     <li>The object is not of type {@code clazz}.
     * </ul>
     *
     * @param key a String, or {@code null}
     * @return a Parcelable[] value, or {@code null}
     */
    @Nullable
    public static <T> T[] getGenericsArray(@NonNull Bundle bundle,
                                           @Nullable String key) {
        final String className = bundle.getString(key + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                final Class<?> clazz = Class.forName(className);
                bundle.setClassLoader(clazz.getClassLoader());
                //noinspection unchecked
                return (T[]) bundle.getParcelableArray(key, clazz);
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return (T[]) bundle.getParcelableArray(key);
        }
    }

    /**
     * Inserts a List of Parcelable values into the mapping of this Bundle,
     * replacing any existing value for the given key.  Either key or value may
     * be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList of Parcelable objects, or null
     */
    public static void putGenericsArrayList(@NonNull Bundle bundle,
                                            @Nullable String key,
                                            @Nullable ArrayList<? extends Parcelable> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String className = null;
            if (value != null) {
                for (Parcelable parcelable : value) {
                    if (parcelable != null) {
                        className = parcelable.getClass().getName();
                        break;
                    }
                }
            }
            if (className != null) {
                bundle.putString(key + SUFFIX, className);
            }
            bundle.putParcelableArrayList(key, value);
        } else {
            bundle.putParcelableArrayList(key, value);
        }
    }

    /**
     * Returns the value associated with the given key, or {@code null} if:
     * <ul>
     *     <li>No mapping of the desired type exists for the given key.
     *     <li>A {@code null} value is explicitly associated with the key.
     *     <li>The object is not of type {@code clazz}.
     * </ul>
     *
     * @param key a String, or {@code null}
     * @return an ArrayList<T> value, or {@code null}
     */
    @Nullable
    public static <T> ArrayList<T> getGenericsArrayList(@NonNull Bundle bundle,
                                                        @Nullable String key) {
        final String className = bundle.getString(key + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                final Class<?> clazz = Class.forName(className);
                bundle.setClassLoader(clazz.getClassLoader());
                //noinspection unchecked
                return (ArrayList<T>) bundle.getParcelableArrayList(key, clazz);
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return (ArrayList<T>) bundle.getParcelableArrayList(key);
        }
    }

    /**
     * Inserts a SparceArray of Parcelable values into the mapping of this
     * Bundle, replacing any existing value for the given key.  Either key
     * or value may be null.
     *
     * @param key   a String, or null
     * @param value a SparseArray of Parcelable objects, or null
     */
    public static void putSparseGenericsArray(@NonNull Bundle bundle,
                                              @Nullable String key,
                                              @Nullable SparseArray<? extends Parcelable> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String className = null;
            if (value != null) {
                final int size = value.size();
                for (int i = 0; i < size; i++) {
                    final Parcelable parcelable = value.valueAt(i);
                    if (parcelable != null) {
                        className = parcelable.getClass().getName();
                        break;
                    }
                }
            }
            if (className != null) {
                bundle.putString(key + SUFFIX, className);
            }
            bundle.putSparseParcelableArray(key, value);
        } else {
            bundle.putSparseParcelableArray(key, value);
        }
    }

    /**
     * Returns the value associated with the given key, or {@code null} if:
     * <ul>
     *     <li>No mapping of the desired type exists for the given key.
     *     <li>A {@code null} value is explicitly associated with the key.
     *     <li>The object is not of type {@code clazz}.
     * </ul>
     *
     * @param key a String, or null
     * @return a SparseArray of T values, or null
     */
    @Nullable
    public static <T> SparseArray<T> getSparseGenericsArray(@NonNull Bundle bundle,
                                                            @Nullable String key) {
        final String className = bundle.getString(key + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                //noinspection unchecked
                return (SparseArray<T>) bundle.getSparseParcelableArray(key,
                        Class.forName(className));
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return (SparseArray<T>) bundle.getSparseParcelableArray(key);
        }
    }

    /**
     * Inserts a Serializable value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Serializable object, or null
     */
    public static void putGenericsSerializable(@NonNull Bundle bundle,
                                               @Nullable String key,
                                               @Nullable Serializable value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (value != null) {
                bundle.putString(key + SUFFIX, value.getClass().getName());
            }
            bundle.putSerializable(key, value);
        } else {
            bundle.putSerializable(key, value);
        }
    }

    /**
     * Returns the value associated with the given key, or {@code null} if:
     * <ul>
     *     <li>No mapping of the desired type exists for the given key.
     *     <li>A {@code null} value is explicitly associated with the key.
     *     <li>The object is not of type {@code clazz}.
     * </ul>
     *
     * @param key a String, or null
     * @return a Serializable value, or null
     */
    @Nullable
    public static <T extends Serializable> T getGenericsSerializable(@NonNull Bundle bundle,
                                                                     @Nullable String key) {
        final String className = bundle.getString(key + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                //noinspection unchecked
                return bundle.getSerializable(key, (Class<T>) Class.forName(className));
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return (T) bundle.getSerializable(key);
        }
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The Parcelable data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putGenericsExtra
     * @see Intent#removeExtra
     * @see #getGenericsExtra(Intent, String)
     */
    @NonNull
    public static Intent putGenericsExtra(@NonNull Intent intent,
                                          String name,
                                          @Nullable Parcelable value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (value != null) {
                intent.putExtra(name + SUFFIX, value.getClass().getName());
            }
            intent.putExtra(name, value);
        } else {
            intent.putExtra(name, value);
        }
        return intent;
    }

    /**
     * Retrieve extended data from the intent.
     *
     * @param name The name of the desired item.
     * @return the value of an item previously added with putExtra(),
     * or null if no Parcelable value was found.
     * @see #putGenericsExtra(Intent, String, Parcelable)
     */
    @Nullable
    public static <T> T getGenericsExtra(@NonNull Intent intent,
                                         @Nullable String name) {
        final String className = intent.getStringExtra(name + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                //noinspection unchecked
                return (T) intent.getParcelableExtra(name, Class.forName(className));
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return intent.getParcelableExtra(name);
        }
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The Parcelable[] data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putGenericsExtra
     * @see Intent#removeExtra
     * @see #getGenericsArrayExtra(Intent, String)
     */
    @NonNull
    public static Intent putGenericsExtra(@NonNull Intent intent,
                                          String name,
                                          @Nullable Parcelable[] value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String className = null;
            if (value != null) {
                for (Parcelable parcelable : value) {
                    if (parcelable != null) {
                        className = parcelable.getClass().getName();
                        break;
                    }
                }
            }
            if (className != null) {
                intent.putExtra(name + SUFFIX, className);
            }
            intent.putExtra(name, value);
        } else {
            intent.putExtra(name, value);
        }
        return intent;
    }

    /**
     * Retrieve extended data from the intent.
     *
     * @param name The name of the desired item.
     * @return the value of an item previously added with putExtra(),
     * or null if no Parcelable[] value was found.
     * @see #putGenericsExtra(Intent, String, Parcelable[])
     */
    @Nullable
    public static <T> T[] getGenericsArrayExtra(@NonNull Intent intent,
                                                @Nullable String name) {
        final String className = intent.getStringExtra(name + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                //noinspection unchecked
                return (T[]) intent.getParcelableArrayExtra(name, Class.forName(className));
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return (T[]) intent.getParcelableArrayExtra(name);
        }
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The ArrayList<Parcelable> data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putGenericsExtra
     * @see Intent#removeExtra
     * @see #getGenericsArrayListExtra(Intent, String)
     */
    @NonNull
    public static Intent putGenericsArrayListExtra(@NonNull Intent intent,
                                                   String name,
                                                   @Nullable ArrayList<? extends Parcelable> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String className = null;
            if (value != null) {
                for (Parcelable parcelable : value) {
                    if (parcelable != null) {
                        className = parcelable.getClass().getName();
                        break;
                    }
                }
            }
            if (className != null) {
                intent.putExtra(name + SUFFIX, className);
            }
            intent.putParcelableArrayListExtra(name, value);
        } else {
            intent.putParcelableArrayListExtra(name, value);
        }
        return intent;
    }

    /**
     * Retrieve extended data from the intent.
     *
     * @param name The name of the desired item.
     * @return the value of an item previously added with
     * putParcelableArrayListExtra(), or null if no
     * ArrayList<Parcelable> value was found.
     * @see #putGenericsArrayListExtra(Intent, String, ArrayList)
     */
    @Nullable
    public static <T> ArrayList<T> getGenericsArrayListExtra(@NonNull Intent intent,
                                                             @Nullable String name) {
        final String className = intent.getStringExtra(name + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                //noinspection unchecked
                return (ArrayList<T>) intent.getParcelableArrayListExtra(name,
                        Class.forName(className));
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return (ArrayList<T>) intent.getParcelableArrayListExtra(name);
        }
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The Serializable data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putGenericsExtra
     * @see Intent#removeExtra
     * @see #getGenericsSerializableExtra(Intent, String)
     */
    @NonNull
    public static Intent putGenericsExtra(@NonNull Intent intent,
                                          String name,
                                          @Nullable Serializable value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (value != null) {
                intent.putExtra(name + SUFFIX, value.getClass().getName());
            }
            intent.putExtra(name, value);
        } else {
            intent.putExtra(name, value);
        }
        return intent;
    }

    /**
     * Retrieve extended data from the intent.
     *
     * @param name The name of the desired item.
     * @return the value of an item previously added with putExtra(),
     * or null if no Serializable value was found.
     * @see #putGenericsExtra(Intent, String, Serializable)
     */
    @Nullable
    public static <T extends Serializable> T getGenericsSerializableExtra(@NonNull Intent intent,
                                                                          @Nullable String name) {
        final String className = intent.getStringExtra(name + SUFFIX);
        if (className != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            try {
                //noinspection unchecked
                return intent.getSerializableExtra(name, (Class<T>) Class.forName(className));
            } catch (ClassNotFoundException e) {
                return null;
            }
        } else {
            //noinspection unchecked
            return (T) intent.getSerializableExtra(name);
        }
    }
}

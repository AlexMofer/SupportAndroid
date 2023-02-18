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
package com.am.tool.support.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;

import com.am.tool.support.utils.UserDataUtils;

/**
 * 基础账户
 * Created by Alex on 2023/2/18.
 */
public class BaseAccount {

    private final AccountManager mManager;
    private final Account mAccount;

    public BaseAccount(AccountManager manager, Account account) {
        mManager = manager;
        mAccount = account;
    }

    /**
     * 保存用户数据
     *
     * @param key   数据Key
     * @param value 数据值
     * @return 保存成功时返回true
     */
    protected boolean setUserData(String key, String value) {
        return UserDataUtils.setUserData(mManager, mAccount, key, value);
    }

    /**
     * 获取用户数据
     *
     * @param key          数据Key
     * @param defaultValue 数据默认值
     * @return 数据值
     */
    protected String getUserData(String key, String defaultValue) {
        return UserDataUtils.getUserData(mManager, mAccount, key, defaultValue);
    }

    /**
     * 保存用户数据
     *
     * @param key   数据Key
     * @param value 数据值
     * @return 保存成功时返回true
     */
    protected boolean setUserData(String key, int value) {
        return UserDataUtils.setUserData(mManager, mAccount, key, value);
    }

    /**
     * 获取用户数据
     *
     * @param key          数据Key
     * @param defaultValue 数据默认值
     * @return 数据值
     */
    protected int getUserData(String key, int defaultValue) {
        return UserDataUtils.getUserData(mManager, mAccount, key, defaultValue);
    }

    /**
     * 保存用户数据
     *
     * @param key   数据Key
     * @param value 数据值
     * @return 保存成功时返回true
     */
    protected boolean setUserData(String key, long value) {
        return UserDataUtils.setUserData(mManager, mAccount, key, value);
    }

    /**
     * 获取用户数据
     *
     * @param key          数据Key
     * @param defaultValue 数据默认值
     * @return 数据值
     */
    protected long getUserData(String key, long defaultValue) {
        return UserDataUtils.getUserData(mManager, mAccount, key, defaultValue);
    }

    /**
     * 保存用户数据
     *
     * @param key   数据Key
     * @param value 数据值
     * @return 保存成功时返回true
     */
    protected boolean setUserData(String key, float value) {
        return UserDataUtils.setUserData(mManager, mAccount, key, value);
    }

    /**
     * 获取用户数据
     *
     * @param key          数据Key
     * @param defaultValue 数据默认值
     * @return 数据值
     */
    protected float getUserData(String key, float defaultValue) {
        return UserDataUtils.getUserData(mManager, mAccount, key, defaultValue);
    }

    /**
     * 保存用户数据
     *
     * @param key   数据Key
     * @param value 数据值
     * @return 保存成功时返回true
     */
    protected boolean setUserData(String key, double value) {
        return UserDataUtils.setUserData(mManager, mAccount, key, value);
    }

    /**
     * 获取用户数据
     *
     * @param key          数据Key
     * @param defaultValue 数据默认值
     * @return 数据值
     */
    protected double getUserData(String key, double defaultValue) {
        return UserDataUtils.getUserData(mManager, mAccount, key, defaultValue);
    }

    /**
     * 保存用户数据
     *
     * @param key   数据Key
     * @param value 数据值
     * @return 保存成功时返回true
     */
    protected boolean setUserData(String key, boolean value) {
        return UserDataUtils.setUserData(mManager, mAccount, key, value);
    }

    /**
     * 获取用户数据
     *
     * @param key          数据Key
     * @param defaultValue 数据默认值
     * @return 数据值
     */
    protected boolean getUserData(String key, boolean defaultValue) {
        return UserDataUtils.getUserData(mManager, mAccount, key, defaultValue);
    }
}

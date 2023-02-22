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
import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.am.tool.support.utils.UserDataUtils;

/**
 * 基础账户
 * Created by Alex on 2023/2/18.
 */
public class BaseAccount {

    private static final String KEY_TIMESTAMP_AUTH_TOKEN = "timestamp_auth_token_";
    private final AccountManager mManager;
    private final Account mAccount;
    private final String mAuthTokenType;

    public BaseAccount(AccountManager manager, Account account, String authTokenType) {
        mManager = manager;
        mAccount = account;
        mAuthTokenType = authTokenType;
    }

    /**
     * 设置Auth Token
     * 注意：API 22 及以下需要{@code android.permission.AUTHENTICATE_ACCOUNTS} 权限
     *
     * @param token Auth Token
     * @return 设置成功时返回true
     */
    @SuppressLint("MissingPermission")
    protected static boolean setAuthToken(AccountManager manager, Account account,
                                          String authTokenType, String token) {
        try {
            manager.setAuthToken(account, authTokenType, token);
            UserDataUtils.setUserData(manager, account,
                    KEY_TIMESTAMP_AUTH_TOKEN + authTokenType, System.currentTimeMillis());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取Auth Token时间戳
     *
     * @return Auth Token时间戳
     */
    protected static long getAuthTokenTimestamp(AccountManager manager, Account account,
                                                String authTokenType) {
        return UserDataUtils.getUserData(manager, account,
                KEY_TIMESTAMP_AUTH_TOKEN + authTokenType, 0L);
    }

    /**
     * 获取账户管理器
     *
     * @return 账户管理器
     */
    protected AccountManager getAccountManager() {
        return mManager;
    }

    /**
     * 获取账户
     *
     * @return 账户
     */
    protected Account getAccount() {
        return mAccount;
    }

    /**
     * 获取授权Token类型
     *
     * @return 授权Token类型
     */
    protected String getAuthTokenType() {
        return mAuthTokenType;
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

    /**
     * 获取Auth Token
     * 注意：API 22 及以下需要{@code android.permission.AUTHENTICATE_ACCOUNTS} 权限
     *
     * @return Auth Token
     */
    @SuppressLint("MissingPermission")
    @Nullable
    protected String peekAuthToken() {
        return mManager.peekAuthToken(mAccount, mAuthTokenType);
    }

    /**
     * 设置Auth Token
     * 注意：API 22 及以下需要{@code android.permission.AUTHENTICATE_ACCOUNTS} 权限
     *
     * @param token Auth Token
     * @return 设置成功时返回true
     */
    protected boolean setAuthToken(String token) {
        return setAuthToken(mManager, mAccount, mAuthTokenType, token);
    }

    /**
     * 获取Auth Token时间戳
     *
     * @return Auth Token时间戳
     */
    protected long getAuthTokenTimestamp() {
        return getAuthTokenTimestamp(mManager, mAccount, mAuthTokenType);
    }
}

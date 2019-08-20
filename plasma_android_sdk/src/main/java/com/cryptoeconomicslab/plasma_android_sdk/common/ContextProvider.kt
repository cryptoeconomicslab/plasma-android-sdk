package com.cryptoeconomicslab.plasma_android_sdk.common

import android.annotation.SuppressLint
import android.content.Context
import com.cryptoeconomicslab.plasma_android_sdk.common.error.UnInitializedException

/**
 * Provide {@link android.content.Context} to feature which needs context
 *
 * In general, context should not be a member of static object but allow only in here.
 * This is a common way to provide context to library.
 */
@SuppressLint("StaticFieldLeak")
object ContextProvider {
    private var context: Context? = null

    /**
     * Initialize with application context
     *
     * @param context Application Context
     */
    fun init(context: Context) {
        this.context = context
    }

    /**
     * This method will provide application context
     *
     * @return Application Context
     */
    fun getApplicationContext(): Context {
        this.context?.let {
            return it
        } ?: throw UnInitializedException()
    }
}
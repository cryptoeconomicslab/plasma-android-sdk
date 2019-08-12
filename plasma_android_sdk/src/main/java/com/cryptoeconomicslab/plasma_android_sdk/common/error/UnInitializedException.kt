package com.cryptoeconomicslab.plasma_android_sdk.common.error

/**
 * Exception thrown when {@link android.content.Context} is not initialized
 * Since some of our feature uses {@link android.content.Context} it should be initialized first with Application Context
 */
class UnInitializedException : Exception("Context is null. You need to call PlasmaClient#init(context) first.")

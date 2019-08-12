package com.cryptoeconomicslab.plasma_android_sdk.database

import com.cryptoeconomicslab.plasma_android_sdk.database.dto.SimpleDTO
import com.cryptoeconomicslab.plasma_android_sdk.database.entity.SimpleEntity

internal interface DatabaseContract {
    fun insert(dto: SimpleDTO): Boolean
    fun findAll(): List<SimpleEntity>
}
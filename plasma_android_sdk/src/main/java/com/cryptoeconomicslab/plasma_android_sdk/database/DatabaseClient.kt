package com.cryptoeconomicslab.plasma_android_sdk.database

import com.cryptoeconomicslab.plasma_android_sdk.common.ContextProvider
import com.cryptoeconomicslab.plasma_android_sdk.database.dto.SimpleDTO
import com.cryptoeconomicslab.plasma_android_sdk.database.entity.SimpleEntity

/**
 * TODO : delete this since this is sample method which call for demo
 */
internal class DatabaseClient : DatabaseContract {
    override fun insert(dto: SimpleDTO): Boolean {
        TODO("not implemented")
    }

    override fun findAll(): List<SimpleEntity> {
        val manager = DatabaseHelper(ContextProvider.getApplicationContext())
        return manager.findAll()
    }

    external fun insertFromRust(string: String, executor: DatabaseExecutor)
}
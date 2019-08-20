package com.cryptoeconomicslab.plasma_android_sdk.database

import com.cryptoeconomicslab.plasma_android_sdk.common.ContextProvider
import com.cryptoeconomicslab.plasma_android_sdk.database.dto.SimpleDTO
import com.cryptoeconomicslab.plasma_android_sdk.database.entity.SimpleEntity

/**
 * [Database feature]
 *
 * Executor class open for Rust
 * This should be called only from Rust Layer.
 */
object DatabaseExecutor {
    /**
     * This method allows you to insert SimpleEntity to SQLite database
     * @param data
     * @return true if succeed to save, false for else
     */
    fun insert(data: String) {
        val manager = DatabaseHelper(ContextProvider.getApplicationContext())
        manager.insert(SimpleDTO(str = data))
    }

    /**
     * This method allows you to find all the items in SQLite database
     * @return saved entities, empty when nothing is saved.
     */
    fun findAll(): List<SimpleEntity> {
        val manager = DatabaseHelper(ContextProvider.getApplicationContext())
        return manager.findAll()
    }
}
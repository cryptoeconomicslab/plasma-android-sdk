package com.cryptoeconomicslab.plasma_android_sdk.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.cryptoeconomicslab.plasma_android_sdk.database.dto.SimpleDTO
import com.cryptoeconomicslab.plasma_android_sdk.database.entity.SimpleEntity
import java.util.*

private object DBConstants {
    const val DB_NAME = "sample.db"
    const val DB_VERSION = 1
    const val TABLE_NAME = "sample_table"
    const val COLUMN_ID = "_id"
    const val COLUMN_VALUE = "data"
}

private object Query {
    val createTable = """
        CREATE TABLE ${DBConstants.TABLE_NAME} (
            _id INTEGER PRIMARY KEY AUTOINCREMENT,
            data TEXT NOT NULL
        );
    """.trimIndent()

    val dropTable = """
        DROP TABLE IF EXISTS ${DBConstants.TABLE_NAME};
    """.trimIndent()
}

internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("CEL-CLIENT-SDK", "create table")
        db?.execSQL(Query.createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // TODO : implement migration process
    }

    fun insert(dto: SimpleDTO): Boolean {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBConstants.COLUMN_VALUE, dto.str)

        db.insert(DBConstants.TABLE_NAME, null, values)

        return true
    }

    fun findAll(): List<SimpleEntity> {
        val query = """
            SELECT * FROM ${DBConstants.TABLE_NAME}
        """.trimIndent()

        val db = writableDatabase

        val entities = ArrayList<SimpleEntity>()

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(Query.createTable)
            return emptyList()
        }

        cursor?.let {
            it.moveToFirst()

            while (!cursor.isAfterLast) {
                val dataId = cursor.getInt(cursor.getColumnIndex(DBConstants.COLUMN_ID))
                val dataValue = cursor.getString(cursor.getColumnIndex(DBConstants.COLUMN_VALUE))

                entities.add(SimpleEntity(dataId, dataValue))
                cursor.moveToNext()
            }
        } ?: return emptyList()

        return entities
    }
}
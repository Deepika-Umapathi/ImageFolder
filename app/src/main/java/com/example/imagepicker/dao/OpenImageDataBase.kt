package com.example.imagepicker.dao

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Entity
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.imagepicker.Constants

@Database(entities = [OpenImageEntity::class], version = 1,exportSchema = false)
abstract class OpenImageDataBase:RoomDatabase() {
    abstract fun openImageDao():OpenImageDao?
    companion object{
        private var openImageDataBase:OpenImageDataBase?=null
        fun getOpenFolderDataBase(context: Context):OpenImageDataBase?{
            if(openImageDataBase==null){
                openImageDataBase = Room.databaseBuilder(context.applicationContext,OpenImageDataBase::class.java,"ImageFolderItem")
                    .allowMainThreadQueries()/*.addMigrations(MIGRATIONS)*/.build()
            }
            return openImageDataBase
        }
    }
}
//val MIGRATIONS = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "CREATE TABLE IF NOT EXISTS `createFolder` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `folderUuid` TEXT, `folderName` TEXT)"
//        )
//    }
//}
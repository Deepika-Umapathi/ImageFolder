package com.example.imagepicker.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [FolderEntity::class], version = 1,exportSchema = false)
abstract class FolderDataBase :RoomDatabase(){

    abstract fun folderDao():FolderDao?
    companion object{
       private var folderDataBase:FolderDataBase?=null
        fun getFolderDataBase(context:Context):FolderDataBase?{
            if (folderDataBase==null){
                folderDataBase = Room.databaseBuilder(context.applicationContext,FolderDataBase::class.java,"FolderItem")
                    .allowMainThreadQueries()/*.addMigrations(MIGRATION)*/.build()
            }
            return folderDataBase
        }
    }
}
//val MIGRATION = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "CREATE TABLE IF NOT EXISTS `createFolder` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `folderUuid` TEXT, `folderName` TEXT)"
//        )
//    }
//}
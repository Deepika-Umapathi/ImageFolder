package com.example.imagepicker.dao

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imagepicker.Constants

@Entity(tableName = "Folders")
data class FolderEntity (

    @ColumnInfo(name = Constants.folderUuid)
    var folderUuid:String,
    @ColumnInfo(name = Constants.folderName)
    var name:String)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=Constants.id)var id:Int=0
}

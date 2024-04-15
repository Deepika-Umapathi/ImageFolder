package com.example.imagepicker.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imagepicker.Constants

@Entity(tableName = Constants.OpenImageFolder)
class OpenImageEntity (
    @ColumnInfo(name = Constants.ImageList)
    var folderImageList:String,
    @ColumnInfo(name = Constants.imageUuid)
    var imageUuid:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=Constants.ids)
    var ids:Int=0
}
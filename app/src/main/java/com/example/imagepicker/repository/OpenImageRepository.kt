package com.example.imagepicker.repository

import androidx.lifecycle.LiveData
import com.example.imagepicker.dao.OpenImageDao
import com.example.imagepicker.dao.OpenImageEntity

class OpenImageRepository(private var openImageDao: OpenImageDao?)  {
    var allImageFolders:LiveData<List<OpenImageEntity>>? = openImageDao?.getInsertImage()
    fun insert(imageUrls: List<String>,uuid:String){
        println("allImageFolders $allImageFolders")
        val images = imageUrls.map { OpenImageEntity(folderImageList = it, imageUuid = uuid) }
        openImageDao?.insert(images)
    }
}
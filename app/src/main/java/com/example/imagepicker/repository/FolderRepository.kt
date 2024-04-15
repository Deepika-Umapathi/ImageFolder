package com.example.imagepicker.repository

import androidx.lifecycle.LiveData
import com.example.imagepicker.dao.FolderDao
import com.example.imagepicker.dao.FolderEntity

class FolderRepository(private val folderDao:FolderDao?) {
    val allFolders : LiveData<List<FolderEntity>>? = folderDao?.getFolderName()
    fun insert(folderEntity: FolderEntity){
        folderDao?.insert(folderEntity)
    }
    fun update(folderEntity: FolderEntity){
        folderDao?.update(folderEntity)
    }
    fun delete(folderEntity: FolderEntity){
        folderDao?.delete(folderEntity)
    }
}
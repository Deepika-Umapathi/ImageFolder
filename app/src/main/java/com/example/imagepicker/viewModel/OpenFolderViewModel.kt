package com.example.imagepicker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.imagepicker.dao.FolderDao
import com.example.imagepicker.dao.FolderDataBase
import com.example.imagepicker.dao.OpenImageDao
import com.example.imagepicker.dao.OpenImageDataBase
import com.example.imagepicker.dao.OpenImageEntity
import com.example.imagepicker.repository.OpenImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpenFolderViewModel(application: Application):AndroidViewModel(application) {
    var allImageFolder :LiveData<List<OpenImageEntity>>?=null
    private val openImageRepository:OpenImageRepository
    init {
        val dao = OpenImageDataBase.getOpenFolderDataBase(application)?.openImageDao()
        openImageRepository = OpenImageRepository(dao)
        allImageFolder = openImageRepository.allImageFolders
    }
    fun insert(imageUrls: List<String>,uuid:String)=viewModelScope.launch(Dispatchers.IO){
        openImageRepository.insert(imageUrls, uuid = uuid)
    }
}
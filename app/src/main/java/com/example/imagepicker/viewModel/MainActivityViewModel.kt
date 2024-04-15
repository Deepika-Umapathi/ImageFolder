package com.example.imagepicker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imagepicker.dao.FolderDataBase
import com.example.imagepicker.dao.FolderEntity
import com.example.imagepicker.repository.FolderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application :Application): AndroidViewModel(application) {
     var  allFolders : LiveData<List<FolderEntity>>?=null
    private val folderRepository:FolderRepository
    init{
        val dao = FolderDataBase.getFolderDataBase(application)?.folderDao()
        folderRepository= FolderRepository(dao)
        allFolders = folderRepository.allFolders
    }
    fun insert(folderEntity: FolderEntity)=viewModelScope.launch(Dispatchers.IO){
        folderRepository.insert(folderEntity)
    }
    fun update(folderEntity: FolderEntity)=viewModelScope.launch(Dispatchers.IO){
        folderRepository.update(folderEntity)
    }
    fun delete(folderEntity: FolderEntity)=viewModelScope.launch (Dispatchers.IO){
        folderRepository.delete(folderEntity)
    }
}
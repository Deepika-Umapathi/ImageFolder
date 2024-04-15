package com.example.imagepicker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(folderEntity: FolderEntity)

    @Update
    fun update(folderEntity: FolderEntity)

    @Delete
    fun delete(folderEntity: FolderEntity)

    @Query("SELECT * FROM Folders ORDER BY id ASC ")
    fun getFolderName():LiveData<List<FolderEntity>>
}
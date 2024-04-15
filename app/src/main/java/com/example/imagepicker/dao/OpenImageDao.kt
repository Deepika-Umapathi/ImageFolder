package com.example.imagepicker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface OpenImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(images: List<OpenImageEntity>)
   @Query("SELECT * FROM OpenImageFolder ORDER BY ids ASC")
    fun getInsertImage(): LiveData<List<OpenImageEntity>>
    @Update
    fun update(OpenImageEntity: OpenImageEntity)
}
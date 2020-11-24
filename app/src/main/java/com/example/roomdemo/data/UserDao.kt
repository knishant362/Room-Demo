package com.example.roomdemo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdemo.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_data")
    fun clearDatabase(): Int


}
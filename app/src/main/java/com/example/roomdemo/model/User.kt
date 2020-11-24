package com.example.roomdemo.model

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_data")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val firstName: String,
        val lastName: String,
        val age: String
) : Parcelable
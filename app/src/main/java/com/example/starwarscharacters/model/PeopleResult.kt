package com.example.starwarscharacters.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class People(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var name: String,
    var height: String,
    var mass: String
) : Parcelable

data class PeopleResult(
    var results: List<People>
)
package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "wish-title")
    val title: String = "",

    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(1, "title1", "description1"),
        Wish(2, "title2", "description2"),
        Wish(3, "title3", "description3"),
        Wish(4, "title4", "description4"),
        Wish(5, "title5", "description5")
    )
}
package com.example.roomdatabseexample.model

import androidx.room.*

data class UserListModel(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

@Entity(tableName = "user")
data class Data(

//    @PrimaryKey(autoGenerate = true)
//    val UserId: Int,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "first_name")
    val first_name: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "last_name")
    val last_name: String
) {
    override fun toString(): String{

        return "Data( avatar='$avatar', email='$email', first_name='$first_name', id=$id, last_name='$last_name')"

    }
}

data class Support(
    val text: String,
    val url: String
)

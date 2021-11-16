package com.arthurfp.cookaholic.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.arthurfp.cookaholic.util.Constants.Companion.USERS_TABLE;

@Entity(tableName = USERS_TABLE)
class UsersEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var login: String,
    var pass: String
) {
}
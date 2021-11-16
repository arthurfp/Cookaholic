package com.arthurfp.cookaholic.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arthurfp.cookaholic.util.Constants.Companion.FAVORITE_RECIPES_TABLE
import com.arthurfp.cookaholic.models.Result

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
) {
}
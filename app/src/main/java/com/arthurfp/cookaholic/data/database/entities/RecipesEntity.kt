package com.arthurfp.cookaholic.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arthurfp.cookaholic.models.FoodRecipe
import com.arthurfp.cookaholic.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
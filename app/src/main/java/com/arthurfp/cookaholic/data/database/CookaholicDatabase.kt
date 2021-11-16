package com.arthurfp.cookaholic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arthurfp.cookaholic.data.database.entities.FavoritesEntity
import com.arthurfp.cookaholic.data.database.entities.RecipesEntity
import com.arthurfp.cookaholic.data.database.entities.UsersEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class, UsersEntity::class],
    version = 1, // Schema version (you must upgrade it whenever you change the schema)
    // exportSchema = false --> exportSchema is true by default
)
@TypeConverters(RecipesTypeConverter::class)
abstract class CookaholicDatabase: RoomDatabase() {

    abstract fun recipesDao(): CookaholicDao
}
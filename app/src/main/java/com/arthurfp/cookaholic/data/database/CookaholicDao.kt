package com.arthurfp.cookaholic.data.database

import androidx.room.*
import com.arthurfp.cookaholic.data.database.entities.FavoritesEntity
import com.arthurfp.cookaholic.data.database.entities.RecipesEntity
import com.arthurfp.cookaholic.data.database.entities.UsersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CookaholicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(userEntity: UsersEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes() : Flow<List<RecipesEntity>>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM users_table WHERE login =:login")
    fun readUser(login: String): Flow<UsersEntity>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

}
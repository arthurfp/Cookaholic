package com.arthurfp.cookaholic.data

import com.arthurfp.cookaholic.data.database.CookaholicDao
import com.arthurfp.cookaholic.data.database.entities.FavoritesEntity
import com.arthurfp.cookaholic.data.database.entities.RecipesEntity
import com.arthurfp.cookaholic.data.database.entities.UsersEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val cookaholicDao: CookaholicDao
) {

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        cookaholicDao.insertRecipes(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        cookaholicDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun insertUser(usersEntity: UsersEntity) {
        cookaholicDao.insertUser(usersEntity)
    }

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return cookaholicDao.readRecipes()
    }

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return cookaholicDao.readFavoriteRecipes()
    }

    fun readUser(login: String): Flow<UsersEntity> {
        return cookaholicDao.readUser(login)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        cookaholicDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        cookaholicDao.deleteAllFavoriteRecipes()
    }

}
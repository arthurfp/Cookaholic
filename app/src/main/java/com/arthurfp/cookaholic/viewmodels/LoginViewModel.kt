package com.arthurfp.cookaholic.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.arthurfp.cookaholic.data.Repository
import com.arthurfp.cookaholic.data.database.entities.FavoritesEntity
import com.arthurfp.cookaholic.data.database.entities.RecipesEntity
import com.arthurfp.cookaholic.data.database.entities.UsersEntity
import com.arthurfp.cookaholic.models.FoodRecipe
import com.arthurfp.cookaholic.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    /** ROOM */
    val readUsers: LiveData<UsersEntity>? = null

    fun insertData(userEntity : UsersEntity) {
        viewModelScope.launch(Dispatchers.IO) { // Dispatchers.IO -> database work or IO of files
            repository.local.insertUser(userEntity)
        }
    }

    fun getLoginDetails(login: String) : LiveData<UsersEntity> {
        return repository.local.readUser(login).asLiveData()
    }


}
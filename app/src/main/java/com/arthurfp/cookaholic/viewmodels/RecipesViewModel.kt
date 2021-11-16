package com.arthurfp.cookaholic.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.arthurfp.cookaholic.data.CategoryAndDietType
import com.arthurfp.cookaholic.data.DataStoreRepository
import com.arthurfp.cookaholic.util.Constants.Companion.API_KEY
import com.arthurfp.cookaholic.util.Constants.Companion.DEFAULT_CATEGORY
import com.arthurfp.cookaholic.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.arthurfp.cookaholic.util.Constants.Companion.DEFAULT_RESULTS_NUMBER
import com.arthurfp.cookaholic.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.arthurfp.cookaholic.util.Constants.Companion.QUERY_API_KEY
import com.arthurfp.cookaholic.util.Constants.Companion.QUERY_CATEGORY
import com.arthurfp.cookaholic.util.Constants.Companion.QUERY_DIET
import com.arthurfp.cookaholic.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.arthurfp.cookaholic.util.Constants.Companion.QUERY_NUMBER
import com.arthurfp.cookaholic.util.Constants.Companion.QUERY_SEARCH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.set

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private lateinit var categoryAndDiet: CategoryAndDietType

    var networkStatus = false
    var backOnline = false

    val readCategoryAndDietType = dataStoreRepository.readCategoryAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    /* Save data on dataStore (called when Response is Successful) */
    fun saveCategoryAndDietType() =
        viewModelScope.launch(Dispatchers.IO) {
            if(::categoryAndDiet.isInitialized) {
                dataStoreRepository.saveCategoryAndDietType(
                    categoryAndDiet.selectedCategory,
                    categoryAndDiet.selectedCategoryId,
                    categoryAndDiet.selectedDietType,
                    categoryAndDiet.selectedDietTypeId
                )
            }
        }

    /* Save data locally, but not on dataStore */
    fun saveCategoryAndDietTypeTemp(
        category: String,
        categoryId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        categoryAndDiet = CategoryAndDietType(category, categoryId, dietType, dietTypeId)
    }

    private fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_RESULTS_NUMBER
        queries[QUERY_API_KEY] = API_KEY

        if(::categoryAndDiet.isInitialized){
            queries[QUERY_CATEGORY] = categoryAndDiet.selectedCategory
            queries[QUERY_DIET] = categoryAndDiet.selectedDietType
        } else {
            queries[QUERY_CATEGORY] = DEFAULT_CATEGORY
            queries[QUERY_DIET] = DEFAULT_DIET_TYPE
        }

        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries

    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RESULTS_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries

    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (backOnline) {
            Toast.makeText(getApplication(), "We're back online", Toast.LENGTH_SHORT).show()
            saveBackOnline(false)
        }
    }

}
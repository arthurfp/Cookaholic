package com.arthurfp.cookaholic.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.arthurfp.cookaholic.util.Constants.Companion.DEFAULT_CATEGORY
import com.arthurfp.cookaholic.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.arthurfp.cookaholic.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.arthurfp.cookaholic.util.Constants.Companion.PREFERENCES_CATEGORY
import com.arthurfp.cookaholic.util.Constants.Companion.PREFERENCES_CATEGORY_ID
import com.arthurfp.cookaholic.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.arthurfp.cookaholic.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.arthurfp.cookaholic.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ViewModelScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object Keys {
        val selectedCategory = stringPreferencesKey(PREFERENCES_CATEGORY)
        val selectedCategoryId = intPreferencesKey(PREFERENCES_CATEGORY_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }

    private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

    suspend fun saveCategoryAndDietType(
        category: String,
        categoryId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        context.dataStore.edit { prefs ->
            prefs[Keys.selectedCategory] = category
            prefs[Keys.selectedCategoryId] = categoryId
            prefs[Keys.selectedDietType] = dietType
            prefs[Keys.selectedDietTypeId] = dietTypeId
        }
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[Keys.backOnline] = backOnline
        }
    }

    val readCategoryAndDietType: Flow<CategoryAndDietType> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { prefs ->
            val selectedCategory = prefs[Keys.selectedCategory] ?: DEFAULT_CATEGORY
            val selectedCategoryId = prefs[Keys.selectedCategoryId] ?: 0
            val selectedDietType = prefs[Keys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = prefs[Keys.selectedDietTypeId] ?: 0
            CategoryAndDietType(
                selectedCategory,
                selectedCategoryId,
                selectedDietType,
                selectedDietTypeId
            )
        }

    val readBackOnline: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { prefs ->
            val backOnline = prefs[Keys.backOnline] ?: false
            backOnline
        }
}

data class CategoryAndDietType(
    val selectedCategory: String,
    val selectedCategoryId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)
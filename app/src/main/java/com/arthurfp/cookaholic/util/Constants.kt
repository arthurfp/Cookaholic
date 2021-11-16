package com.arthurfp.cookaholic.util

class Constants {

    companion object {

        const val BASE_URL = "https://api.spoonacular.com"
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
        const val API_KEY = "24c1b9533d2c4eb292942e513671118e"

        // API query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_CATEGORY = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        // ROOM Database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"
        const val FAVORITE_RECIPES_TABLE = "favorite_recipes_table"
        const val USERS_TABLE = "users_table"

        // Splash Screen
        const val SPLASH_SCREEN_DURATION = 0L//10000L

        // Preferences
        const val DEFAULT_CATEGORY = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"
        const val DEFAULT_RESULTS_NUMBER = "30"

        const val PREFERENCES_NAME = "cookaholic_preferences"

        const val PREFERENCES_CATEGORY = "category"
        const val PREFERENCES_CATEGORY_ID = "categoryId"
        const val PREFERENCES_DIET_TYPE = "dietType"
        const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"
        const val PREFERENCES_BACK_ONLINE = "backOnline"

        const val RECIPE_RESULT_KEY = "recipeBundle"


    }
}
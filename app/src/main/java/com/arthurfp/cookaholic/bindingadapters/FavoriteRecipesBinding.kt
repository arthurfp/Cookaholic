package com.arthurfp.cookaholic.bindingadapters

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arthurfp.cookaholic.adapters.FavoriteRecipesAdapter
import com.arthurfp.cookaholic.data.database.entities.FavoritesEntity

object FavoriteRecipesBinding {

    /*
    setVisibility -> uses favoritesEntity
    setData -> uses mAdatpter
    ("requireAll = false" allows the method to does not need all parameters)
    */
    @BindingAdapter("setVisibility", "setData", requireAll = false)
    @JvmStatic
    fun setVisibility(view: View, favoritesEntity: List<FavoritesEntity>?, mAdapter: FavoriteRecipesAdapter?) {
        when (view) {
            is RecyclerView -> {
                val dataCheck = favoritesEntity.isNullOrEmpty()
                view.isInvisible = dataCheck
                if(!dataCheck){
                    favoritesEntity?.let { mAdapter?.setData(it) }
                }
            }
            else -> view.isVisible = favoritesEntity.isNullOrEmpty()
        }
    }

}
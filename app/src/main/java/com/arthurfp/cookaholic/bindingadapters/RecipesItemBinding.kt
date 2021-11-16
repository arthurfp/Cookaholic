package com.arthurfp.cookaholic.bindingadapters

import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.arthurfp.cookaholic.R
import com.arthurfp.cookaholic.models.Result
import com.arthurfp.cookaholic.ui.fragments.recipes.RecipesFragmentDirections

object RecipesItemBinding {

    @BindingAdapter("onRecipeClickListener")
    @JvmStatic
    fun onRecipeClickListener(recipeItemLayout: ConstraintLayout, result: Result) {
        recipeItemLayout.setOnClickListener {
            try {
                val action =
                    RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)

                recipeItemLayout.findNavController().navigate(action)
            } catch (e: Exception) {
                Log.d("onRecipeClickListener", e.toString())
            }
        }
    }

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic // Make the function static to be called from other languages (e.g., java)
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(600)
            error(R.drawable.recipes_placeholder)
        }
    }

    @BindingAdapter("setHtmlText")
    @JvmStatic
    fun setHtmlText(textView: TextView, text: String) {
        textView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    }

    @BindingAdapter("applyVeganColor")
    @JvmStatic
    fun applyVeganColor(view: View, vegan: Boolean) {
        if (vegan) {
            when (view) {
                is TextView -> {
                    view.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }
                is ImageView -> {
                    view.setColorFilter(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }
            }
        }
    }
}
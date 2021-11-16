package com.arthurfp.cookaholic.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.arthurfp.cookaholic.databinding.RecipesBottomSheetBinding
import com.arthurfp.cookaholic.util.Constants.Companion.DEFAULT_CATEGORY
import com.arthurfp.cookaholic.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.arthurfp.cookaholic.viewmodels.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class RecipesBottomSheet : BottomSheetDialogFragment() {

    private var _binding: RecipesBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipesViewModel: RecipesViewModel

    private var categoryChip = DEFAULT_CATEGORY
    private var categoryChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = RecipesBottomSheetBinding.inflate(inflater, container, false)

        recipesViewModel.readCategoryAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->
            categoryChip = value.selectedCategory
            setChip(value.selectedCategoryId, binding.categoryChipGroup)
            setChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
        })

        binding.categoryChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedCategory = chip.text.toString().lowercase(Locale.ROOT)
            categoryChip = selectedCategory
            categoryChipId = selectedChipId
        }

        binding.dietTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = selectedChipId
        }

        binding.applyBtn.setOnClickListener {
            recipesViewModel.saveCategoryAndDietTypeTemp(
                categoryChip,
                categoryChipId,
                dietTypeChip,
                dietTypeChipId
            )

            val action =
                RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)

            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                val targetChip = chipGroup.findViewById<Chip>(chipId)
                targetChip.isChecked = true
                chipGroup.requestChildFocus(targetChip, targetChip) // focus on selected Chip
            } catch (e: Exception) {
                Log.d("RecipesBottomSheet", e.message.toString())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
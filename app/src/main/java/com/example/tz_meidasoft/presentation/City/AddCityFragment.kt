package com.example.tz_meidasoft.presentation.City

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tz_meidasoft.databinding.FragmentAddCityBinding
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.RuntimeException


class AddCityFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddCityBinding?= null
    private val binding : FragmentAddCityBinding
        get() = _binding ?: throw RuntimeException("FragmentAddCityBinding == null")

    private val viewModel: AddCityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.add.setOnClickListener {
            addCity()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun addCity(){
        val name = binding.nameCity.text.toString()
        if (name.isNotBlank()){
            val cityDomain = CityDomain(null, name, false)
            viewModel.insertCity(cityDomain)
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
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

    private var isEdit = false
    private var city:CityDomain?=null


    override fun onResume() {
        super.onResume()
        viewModel.getIsEdit().observe(viewLifecycleOwner){
            isEdit = it
        }

        if (isEdit){
            viewModel.getCity().observe(viewLifecycleOwner){
                city = it
                setData()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.add.setOnClickListener {
            if (isEdit){
                updateCity()
            } else {
                addCity()
            }
            dismiss()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun addCity(){
        val name = binding.nameCity.text.toString()
        if (validCity(name)){
            val cityDomain = CityDomain(null, name, false)
            viewModel.insertCity(cityDomain)
        }
    }

    private fun updateCity(){
        val name = binding.nameCity.text.toString()
        if (validCity(name)){
            val updateCity = city?.used?.let {
                CityDomain(
                    id = city?.id,
                    city = name,
                    used = it
                )
            }
            if (updateCity != null) {
                viewModel.updateCity(updateCity)
            }
        }
    }

    private fun validCity(cityName: String):Boolean{
        return cityName.isNotBlank()
    }

    private fun setData(){
        binding.nameCity.setText(city?.city ?: "")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
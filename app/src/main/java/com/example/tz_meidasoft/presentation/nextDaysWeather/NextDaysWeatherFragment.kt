package com.example.tz_meidasoft.presentation.nextDaysWeather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tz_meidasoft.databinding.NextDaysWeatherFragmentBinding
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterNextDays
import java.lang.RuntimeException

class NextDaysWeatherFragment : Fragment() {


    private var _binding: NextDaysWeatherFragmentBinding? = null
    private val binding : NextDaysWeatherFragmentBinding
        get() = _binding ?: throw RuntimeException("NextDaysWeatherFragmentBinding == null")

    private val sharedViewModel : SharedViewModelNextDays by activityViewModels()
    private var response:ApiDomain?=null

    override fun onResume() {
        getResponseData()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NextDaysWeatherFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getResponseData(){
        sharedViewModel.getResponse().observe(viewLifecycleOwner){
            response = it
            setData()
        }
    }

    private fun setData(){
        binding.weatherRecycler.adapter = response?.let {
            AdapterNextDays(it.list)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
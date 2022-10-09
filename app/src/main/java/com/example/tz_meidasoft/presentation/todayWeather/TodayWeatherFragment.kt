package com.example.tz_meidasoft.presentation.todayWeather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tz_meidasoft.app.App
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.apiModel.ObjectTempAndWeather
import com.example.tz_meidasoft.databinding.FragmentTodayWeatherBinding
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import com.example.tz_meidasoft.presentation.adapter.AdapterToday.AdapterTodayNextDays
import com.example.tz_meidasoft.presentation.nextDaysWeather.SharedViewModelNextDays
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class TodayWeatherFragment : Fragment() {

    private var _binding: FragmentTodayWeatherBinding? = null
    private val binding : FragmentTodayWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentTodayWeatherBinding == null")

    private var response: ApiDomain?=null
    private var city: CityDomain?=null

    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    private val weatherViewModel: WeatherViewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
    }
    private val sharedViewModel : SharedViewModelNextDays by activityViewModels()
    private val component by lazy{
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        validInternet()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forecast.setOnClickListener {
            response?.let {
                responseData -> sharedViewModel.setResponse(responseData)
                findNavController().navigate(R.id.action_todayWeatherFragment_to_nextDaysWeatherFragment)
            }
        }

        binding.toolbar.exit.setOnClickListener {
            findNavController().navigate(R.id.action_todayWeatherFragment_to_chooseCityFragment)
        }
    }

    private fun setDataWeather() {

        if (response != null){
            binding.nameCity.text = response?.city?.name
            binding.degreeWeather.text = response?.list?.get(0)?.temp?.day?.roundToInt()?.toString()
            binding.humidity.text = response!!.list[0].humidity.toString()
            binding.pressure.text = response!!.list[0].pressure.toString()
            binding.speedWind.text = response!!.list[0].speed.toString()

            if (response?.list?.size?:0 >= 3){
                setAdapter()
            }
        } else {
            weatherViewModel.toast(requireContext(), "Ошибка названия города, поменяйте название...")
            findNavController().navigate(R.id.action_todayWeatherFragment_to_chooseCityFragment)
        }

    }

    private fun loader(){
        if (response != null){
            binding.infoWeather.visibility = View.VISIBLE
            binding.layoutNextDays.visibility = View.VISIBLE
            binding.layoutDegree.visibility = View.VISIBLE
            binding.nameCity.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
        }else {
            binding.progress.visibility = View.VISIBLE
            binding.nameCity.visibility = View.GONE
            binding.infoWeather.visibility = View.GONE
            binding.layoutNextDays.visibility = View.GONE
            binding.layoutDegree.visibility = View.GONE
        }
    }

    private fun getUsedCity(){
        loader()
        weatherViewModel.getUsedCity().observe(viewLifecycleOwner){
            city = it
            getWeatherApi()
        }
    }

    private fun getWeatherApi(){

        city?.city?.let { cityName ->
            weatherViewModel.getWeatherCity(cityName)
        }

        weatherViewModel.responseApi.observe(viewLifecycleOwner){
            response = it.body()
            setDataWeather()
            loader()
        }
    }

    private fun setAdapter() {

        if (response != null){
            val list = ArrayList<ObjectTempAndWeather>()
            response?.list?.get(0)?.let { response ->
                list.add(response.let { listApi ->
                    ObjectTempAndWeather("Сегодня", listApi.temp, listApi.weather[0])
                })
            }
            response?.list?.get(1)?.let { response ->
                list.add(response.let { listApi ->
                    ObjectTempAndWeather("Сегодня", listApi.temp, listApi.weather[0])
                })
            }
            response?.list?.get(2)?.let { response ->
                list.add(response.let { listApi ->
                    ObjectTempAndWeather("Сегодня", listApi.temp, listApi.weather[0])
                })
            }

            binding.nextDaysRecycler.adapter = AdapterTodayNextDays(list)
        }
    }

    private fun validInternet(){
        if (!isConnect()){
            DialogConnect().apply {
                isCancelable = false
                show(childFragmentManager, this.tag)
            }
        } else {
            getUsedCity()
        }
    }

    private fun isConnect () : Boolean {
        val connectManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork: NetworkInfo? = connectManager.activeNetworkInfo
        val isConnect = activityNetwork?.isConnectedOrConnecting

        return isConnect == true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
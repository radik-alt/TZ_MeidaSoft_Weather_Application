package com.example.tz_meidasoft.presentation.TodayWeather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tz_meidasoft.App.App
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.apiModel.ObjectTempAndWeather
import com.example.tz_meidasoft.databinding.FragmentTodayWeatherBinding
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import com.example.tz_meidasoft.presentation.adapter.AdapterToday.AdapterTodayNextDays
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.collections.ArrayList


class TodayWeatherFragment : Fragment() {

    private var _binding: FragmentTodayWeatherBinding? = null
    private val binding : FragmentTodayWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentTodayWeatherBinding == null")


    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory

    private val weatherViewModel: WeatherViewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
    }

    private val sharedViewModel : SharedViewModelNextDays by activityViewModels()

    private val component by lazy{
        (requireActivity().application as App).component
    }

    private var response: ApiDomain?=null
    private var city: CityDomain?=null

    override fun onAttach(context: Context) {
        component.injectToadyWeatherFragment(this)
        super.onAttach(context)
    }

    override fun onResume() {
        if (!isConnect()){
            val dialog = DialogConnect()
            dialog.isCancelable = false
            dialog.show(childFragmentManager, dialog.tag)
        } else {
            getUsedCity()
        }
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayWeatherBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.forecast.setOnClickListener {
            response?.let { it1 -> sharedViewModel.setResponse(it1) }
            findNavController().navigate(R.id.action_todayWeatherFragment_to_nextDaysWeatherFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDataWeather() {

        if (response != null){
            binding.nameCity.text = response?.city?.name
            binding.degreeWeather.text = Math.round(response?.list?.get(0)?.temp?.day!!).toString()
            binding.humidity.text = response!!.list[0].humidity.toString()
            binding.pressure.text = response!!.list[0].pressure.toString()
            binding.speedWind.text = response!!.list[0].speed.toString()

            if (response?.list?.size?:0 >= 3){
                setAdapter()
            }
        } else {
             Toast.makeText(requireContext(), "Ошибка названия города, поменяйте название...", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

    }

    private fun delayGetDataFromApi(){
        if (response != null){
            binding.layoutAllView.visibility = View.VISIBLE
            binding.layoutProgress.visibility = View.GONE
        }else {
            binding.layoutProgress.visibility = View.VISIBLE
            binding.layoutAllView.visibility = View.GONE
        }
    }

    private fun getUsedCity(){
        delayGetDataFromApi()
        weatherViewModel.getUsedCity().observe(viewLifecycleOwner){
            city = it
            getWeatherApi()
        }
    }

    private fun getWeatherApi(){
        weatherViewModel.getWeatherCity(city?.city ?: "Москва")
        weatherViewModel.responseApi.observe(viewLifecycleOwner){
            response = it.body()
            delayGetDataFromApi()
            setDataWeather()
        }
    }

    private fun setAdapter() {
        val list = ArrayList<ObjectTempAndWeather>()
        if (response != null){
            list.add(ObjectTempAndWeather("Сегодня", response!!.list[0].temp, response!!.list[0].weather[0]))
            list.add(ObjectTempAndWeather("Завтра", response!!.list[1].temp, response!!.list[1].weather[0]))
            list.add(ObjectTempAndWeather("Послезавтра", response!!.list[2].temp, response!!.list[2].weather[0]))
            binding.nextDaysRecycler.adapter = AdapterTodayNextDays(list)
        }
    }

    private fun isConnect () : Boolean {
        val connectManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork: NetworkInfo? = connectManager.activeNetworkInfo
        val isConnect = activityNetwork?.isConnectedOrConnecting

        return isConnect == true
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.setting -> {
                findNavController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
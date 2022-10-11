package com.example.tz_meidasoft.presentation.сity

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.app.App
import com.example.tz_meidasoft.databinding.ChooseCityFragmentBinding
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.presentation.adapter.Interface.ChooseCity
import com.example.tz_meidasoft.presentation.adapter.ChooseCityAdapter.AdapterChooseCity
import com.example.tz_meidasoft.presentation.todayWeather.WeatherViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants
import java.lang.RuntimeException
import javax.inject.Inject

class ChooseCityFragment : Fragment() {

    private var _binding: ChooseCityFragmentBinding?= null
    private val binding : ChooseCityFragmentBinding
        get() = _binding ?: throw RuntimeException("ChooseCityFragmentBinding == null")

    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    private val viewModel: ChooseCityViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ChooseCityViewModel::class.java]
    }
    private val sharedCityViewModel : AddCityViewModel by activityViewModels ()
    private val component by lazy{
        (requireActivity().application as App).component
    }


    override fun onResume() {
        super.onResume()
        updateList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChooseCityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addCity.setOnClickListener {
            showBottomFragment()
        }

    }

    private fun showBottomFragment(){
        val bottomDialog = AddCityFragment()
        bottomDialog.show(parentFragmentManager, bottomDialog.tag)
    }

    private fun updateList(){
        viewModel.getDataList().observe(viewLifecycleOwner){
            setAdapter(it)
        }
    }

    private fun deleteCity(id:Long){
        viewModel.deleteCity(id)
     }

    private fun setAdapter(cityList: List<CityDomain>){
        val adapter =  AdapterChooseCity (object : ChooseCity {
            override fun selectCity(city: CityDomain, isEdit: Boolean) {

                if (isEdit){
                    sharedCityViewModel.setEdit(isEdit)
                    sharedCityViewModel.setCity(city)
                    showBottomFragment()
                } else {
                    val selectCity = CityDomain(
                        id = city.id,
                        city = city.city.trim(),
                        used = true
                    )
                    viewModel.setOtherCityNotUsed()
                    viewModel.updateCity(selectCity)
                    findNavController().navigate(R.id.action_chooseCityFragment_to_todayWeatherFragment)
                }


            }
        })

        adapter.setNewListData(cityList)
        binding.cityWeatherAdapter.adapter = adapter

        val simpleCallbackRecycler = ItemTouchHelper(simpleCallback)
        simpleCallbackRecycler.attachToRecyclerView(binding.cityWeatherAdapter)

    }

    private val simpleCallback :ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }


        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            when(direction){

                ItemTouchHelper.LEFT -> {

                    val city = viewModel.getItemCity(viewHolder.absoluteAdapterPosition)
                    if (city != null) {
                        city.id?.let { deleteCity(it) }
                        viewModel.showSnackBar(
                            requireView(),
                            "Удален город ${city.city}",
                            city
                        )
                        updateList()
                    }

                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
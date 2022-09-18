package com.example.tz_meidasoft.presentation.City

import android.graphics.Canvas
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.CityMapper
import com.example.tz_meidasoft.data.entity.dbModel.City
import com.example.tz_meidasoft.databinding.ChooseCityFragmentBinding
import com.example.tz_meidasoft.domain.entity.CityDomain
import com.example.tz_meidasoft.presentation.adapter.Interface.ChooseCity
import com.example.tz_meidasoft.presentation.adapter.ChooseCityAdapter.AdapterChooseCity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class ChooseCityFragment : Fragment() {

    private var _binding: ChooseCityFragmentBinding?= null
    private val binding : ChooseCityFragmentBinding
        get() = _binding ?: throw RuntimeException("ChooseCityFragmentBinding == null")

    private lateinit var viewModel: ChooseCityViewModel
    private val sharedCityViewModel : AddCityViewModel by activityViewModels ()
    private val cityList: ArrayList<CityDomain> = ArrayList()

    private var adapter: AdapterChooseCity?=null

    override fun onResume() {
        super.onResume()
        updateList()
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
        viewModel = ViewModelProvider(this)[ChooseCityViewModel::class.java]

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
            if (cityList.isNotEmpty()){
                cityList.clear()
            }
            cityList.addAll(it)
            setAdapter()
        }
    }

    private fun deleteCity(id:Long){
        viewModel.deleteCity(id)
     }

    private fun addCity(city:CityDomain){
        viewModel.insertCity(city)
    }

    private fun setAdapter(){
        adapter =  AdapterChooseCity (object : ChooseCity {
            override fun selectCity(city: CityDomain, isEdit: Boolean) {
                val tempCity = CityDomain(
                    id = city.id,
                    city = city.city.trim(),
                    used = true
                )

                if (isEdit){
                    sharedCityViewModel.setEdit(isEdit)
                    sharedCityViewModel.setCity(city)
                    showBottomFragment()
                } else {
                    viewModel.setOtherCityNotUsed()
                    viewModel.updateCity(tempCity)
                    Navigation.findNavController(requireView()).navigate(R.id.action_chooseCityFragment_to_todayWeatherFragment)
                }


            }
        })

        adapter?.setNewListData(cityList)
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
                    val city = cityList[viewHolder.adapterPosition]
                    val idCity = city.id
                    if (idCity != null) {
                        deleteCity(idCity)
                    }
                    Snackbar.make(binding.cityWeatherAdapter, "Удален город ${cityList[viewHolder.adapterPosition].city}", Snackbar.LENGTH_SHORT)
                        .setAction("Отмена", View.OnClickListener {
                            addCity(city)
                        }).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
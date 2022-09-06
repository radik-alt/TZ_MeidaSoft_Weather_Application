package com.example.tz_meidasoft.presentation.TodayWeather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.databinding.DialogConnectBinding
import com.example.tz_meidasoft.databinding.FragmentTodayWeatherBinding
import java.lang.RuntimeException

class DialogConnect : DialogFragment() {


    private var _binding: DialogConnectBinding? = null
    private val binding : DialogConnectBinding
        get() = _binding ?: throw RuntimeException("DialogConnectBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogConnectBinding.inflate(inflater, container, false)


        binding.clickConnect.setOnClickListener {
            if (isConnect()){
                binding.clickConnect.text = "Закрыть!"
                binding.process.text = "Соединение установлено!"
                dismiss()
            } else {
                binding.clickConnect.text = "Повторить попытку!"
                binding.process.text = "Соединение прервано..."
            }
        }

        return binding.root
    }

    private fun isConnect () : Boolean {
        val connectManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork: NetworkInfo? = connectManager.activeNetworkInfo
        val isConnect = activityNetwork?.isConnectedOrConnecting

        return isConnect == true
    }

}
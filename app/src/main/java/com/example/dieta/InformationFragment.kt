package com.example.dieta

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dieta.databinding.FragmentInformationBinding
import com.example.dieta.databinding.FragmentStartBinding
import com.example.dieta.model.SharedViewModel
import kotlinx.android.synthetic.main.fragment_information.*
import kotlinx.android.synthetic.main.row_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL= "https://raw.githubusercontent.com/terrenjpeterson/caloriecounter/master/src/data/"


class InformationFragment : Fragment() {

    private var binding: FragmentInformationBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()

    lateinit var adapterItem: AdapterItem
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getMyData()

        val fragmentBinding = FragmentInformationBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this.context)
        recyclerview_lista.setHasFixedSize(true)
        recyclerview_lista.layoutManager = linearLayoutManager

        binding?.apply {
            viewModel = sharedViewModel
            informationFragment = this@InformationFragment
            lifecycleOwner = viewLifecycleOwner
        }

    }

    fun onCalorieTotalChanged(total: Int) {
        suma_txt.text = "Totalul de calorii consumate astazi este: $total"
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!
                adapterItem = AdapterItem(responseBody, this@InformationFragment)
                adapterItem.notifyDataSetChanged()
                recyclerview_lista.adapter=adapterItem
            }


            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("informationFragment", "onFailure: " + t.message)
            }
        })
    }
}
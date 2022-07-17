package com.example.dieta.model

import android.os.Build.VERSION_CODES.O
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dieta.R

class SharedViewModel : ViewModel() {
    private var _nume= MutableLiveData<String>("")
    val nume : LiveData<String> = _nume


    private var _gen= MutableLiveData<String>("")
    val gen : LiveData<String> = _gen


    private var _inaltime= MutableLiveData<Double>(0.0)
    val inaltime : LiveData<Double> = _inaltime


    private var _greutate= MutableLiveData<Double>(0.0)
    val greutate : LiveData<Double> = _greutate

    private var _activitate= MutableLiveData<String>("")
    val activitate : LiveData<String> = _activitate

    private var _ideal= MutableLiveData<Double>(0.0)
    val ideal : LiveData<Double> = _ideal


    fun setNumele(Numele: String){
        _nume.value= "$Numele "
    }

    fun setGen(Gen: String){
        _gen.value= Gen
    }

    fun setInaltime(Inaltime: Double){
        _inaltime.value= Inaltime
    }

    fun setGreutate(Greutate: Double){
        _greutate.value= Greutate
    }

    fun setActivitate(Activitate: String){
        _activitate.value= Activitate
        calculIdeal()
    }



    private fun calculIdeal(){


        if(_gen.value=="Barbat"){
            _ideal.value = 2200.0
        }
        if(_gen.value=="Femeie"){
            _ideal.value = 1800.0
        }

        if( (_inaltime.value ?: 0.0) - 100 == (_greutate.value ?: 0.0))
        {
            _ideal.value= (_ideal.value ?: 0.0)
        }
        if(( _greutate.value ?: 0.0)  +10 <= (_inaltime.value ?: 0.0) -100)
        {
            _ideal.value= (_ideal.value ?: 0.0) +300
        }
        if(( _greutate.value ?: 0.0)  -10 >= (_inaltime.value ?: 0.0) -100)
        {
            _ideal.value= (_ideal.value ?: 0.0) -300
        }

        when(_activitate.value)
        {
            "Deloc" -> {
                _ideal.value =(_ideal.value ?: 0.0)
            }
            "O data pe saptamana" -> {
                _ideal.value = (_ideal.value ?: 0.0) + 200
            }
            "De trei sau mai multe ori pe saptamana" -> {
                _ideal.value = (_ideal.value ?: 0.0) + 500
            }
        }

    }


}
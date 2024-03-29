package com.example.dieta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.dieta.databinding.FragmentResultBinding
import com.example.dieta.databinding.FragmentStartBinding
import com.example.dieta.model.SharedViewModel
import kotlinx.android.synthetic.main.fragment_start.*


class ResultFragment : Fragment() {

    private var binding: FragmentResultBinding?   =null
    private  val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentResultBinding.inflate(inflater, container, false)
        binding= fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel= sharedViewModel
            resultFragment=this@ResultFragment
            lifecycleOwner= viewLifecycleOwner
        }
    }


    fun goToNextScreen3() {
       
        findNavController().navigate(R.id.action_resultFragment_to_informationFragment)
    }


}
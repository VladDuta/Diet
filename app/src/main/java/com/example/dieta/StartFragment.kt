package com.example.dieta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.dieta.databinding.FragmentStartBinding
import com.example.dieta.model.SharedViewModel


class StartFragment : Fragment() {

    private var binding: FragmentStartBinding?   =null
    private  val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding= fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel= sharedViewModel
            startFragment=this@StartFragment
            lifecycleOwner= viewLifecycleOwner
        }
    }

 fun goToNextScreen() {

     val nume = binding?.numeEditText?.text.toString()
     sharedViewModel.setNumele(nume)


     val inaltime = binding?.inaltimeEditText?.text.toString().toDouble()
     sharedViewModel.setInaltime(inaltime)

     val greutate = binding?.greutateEditText?.text.toString().toDouble()
     sharedViewModel.setGreutate(greutate)



     findNavController().navigate(R.id.action_startFragment_to_resultFragment)
 }



}
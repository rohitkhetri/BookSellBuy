package com.example.booksellbuy.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booksellbuy.R
import com.example.booksellbuy.RecyclerView_Fragment.RecyclerView_OneStandard
import com.example.booksellbuy.databinding.FragmentCategoryBinding
import com.example.booksellbuy.databinding.FragmentOnetotenFragmentBinding

class onetoten_fragment : Fragment() {

    private lateinit var binding: FragmentOnetotenFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnetotenFragmentBinding.inflate(layoutInflater)

//        binding.button1.setOnClickListener{
//            findNavController().navigate(R.id.action_onetoten_fragment_to_recyclerView_OneStandard)
//        }

        binding.button1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("bookStandard", "1st Standard")
            findNavController().navigate(R.id.action_onetoten_fragment_to_recyclerView_OneStandard, bundle)
        }

        binding.button2.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("bookStandard", "2st Standard")
            findNavController().navigate(R.id.action_onetoten_fragment_to_recyclerView_OneStandard, bundle)
        }

        binding.button3.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("bookStandard", "3st Standard")
            findNavController().navigate(R.id.action_onetoten_fragment_to_recyclerView_OneStandard, bundle)
        }



//;l
//        binding.button2.setOnClickListener{
//            findNavController().navigate(R.id.action_onetoten_fragment_to_fragment_3rdStandard)
//
//        }

//        binding.button2.setOnClickListener {
//            val action = onetoten_fragment.action("2nd Standard")
//            findNavController().navigate(action)
//        }

//        binding.button2.setOnClickListener {
//            replaceFragment("2nd Standard")
//        }

        return binding.root

    }

//    private fun replaceFragment(standard: String) {
//        val fragment = RecyclerView_OneStandard.newInstance(standard)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, fragment)
//            .commit()
//    }


}
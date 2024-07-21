package com.example.booksellbuy.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentEngineeringBinding
import com.example.booksellbuy.databinding.FragmentOnetotenFragmentBinding

class Engineering_Fragment : Fragment() {

    private lateinit var binding: FragmentEngineeringBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEngineeringBinding.inflate(layoutInflater)

        return binding.root

    }

}
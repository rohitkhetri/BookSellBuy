package com.example.booksellbuy.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentCategoryBinding

class Category_Fragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)


        binding.onetotencard.setOnClickListener{

            findNavController().navigate(R.id.action_categoryFragment_to_onetoten_fragment)

        }

        binding.eleventwelvecard.setOnClickListener{

            findNavController().navigate(R.id.action_categoryFragment_to_elevenTwelve_Fragment)
        }

        binding.graduationcard.setOnClickListener{

            findNavController().navigate(R.id.action_categoryFragment_to_graduation_Fragment)
        }

        binding.engineeringcard.setOnClickListener{

            findNavController().navigate(R.id.action_categoryFragment_to_engineering_Fragment)
        }

        return binding.root

    }
}
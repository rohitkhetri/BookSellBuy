package com.example.booksellbuy.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentCategoryBinding


class Category_Fragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentCategoryBinding.inflate(layoutInflater)


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_, container, false)
    }
}
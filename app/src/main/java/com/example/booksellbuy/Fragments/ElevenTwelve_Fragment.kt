package com.example.booksellbuy.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentElevenTwelveBinding

class ElevenTwelve_Fragment : Fragment() {

    private lateinit var binding: FragmentElevenTwelveBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentElevenTwelveBinding.inflate(layoutInflater)
        return binding.root
    }

}
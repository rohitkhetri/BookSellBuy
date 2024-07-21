package com.example.booksellbuy.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth

class Welcome_Fragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        setupWelcomeMessage()

        binding.logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            findNavController().navigate(R.id.action_welcome_Fragment_to_signup_Fragment)
        }

        return binding.root

    }

    private fun setupWelcomeMessage() {
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            val welcomeMessage = "Welcome, ${it.email}!"
            binding.welcomeusername.text = welcomeMessage
        }
    }
}
package com.example.booksellbuy.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth

class Account_Fragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        Onsignup()
        checkUserLoggedInState()

        binding.loginButton.setOnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Navigate to HomeFragment after successful login
                        findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
                    } else {
                        Toast.makeText(
                            requireContext(), it.exception.toString(), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        // Check user's login state when view is created
//        checkUserLoggedInState()
//    }

    private fun Onsignup() {
        binding.signupText.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_signup_Fragment)
        }
    }

    private fun checkUserLoggedInState() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            // User is logged in, navigate to WelcomeFragment
            findNavController().navigate(R.id.action_accountFragment_to_welcome_Fragment)
        }
        // No need to handle else case here because you want to stay on AccountFragment
        // and show login options if user is not logged in
    }
}

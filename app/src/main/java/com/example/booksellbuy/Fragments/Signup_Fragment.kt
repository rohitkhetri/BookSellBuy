package com.example.booksellbuy.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class Signup_Fragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupBinding.inflate(inflater, container, false)

        var firebaseAuth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener {

            val email = binding.edtusername.text.toString()
            val password = binding.edtpassword.text.toString()
            val confirmpassword = binding.edtconfirmpassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()) {
                if (password == confirmpassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                findNavController().navigate(R.id.action_signup_Fragment_to_homeFragment)
//                                val intent = Intent(requireContext(), Account_Fragment::class.java)
//                                startActivity(intent)
//                                requireActivity().finish()
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    it.exception.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Password is not matching",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Empty fields are not allowed",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
        return binding.root
    }
}

package com.example.booksellbuy.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.booksellbuy.Activity.BookInfo
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentAddBooksBinding
import com.example.bookssellbuy.Object_File.Category
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Locale

class AddBooks_Fragment : Fragment() {

    private var _binding: FragmentAddBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var databaseReference: DatabaseReference
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBooksBinding.inflate(inflater, container, false)

        val galleryLauncher =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    Log.d("Photo", "Selected URI: $uri")
                    imageUri = uri
                    binding.imageViewBookCover.setImageURI(imageUri)
                } else {
                    Log.d("Photo", "No media selected")
                }
            }

        binding.buttonSelectImage.setOnClickListener {
            galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("books")

        binding.buttonAddBook.setOnClickListener {
            addBookToFirebase()
        }
        setAutoCompleteTextView()

        return binding.root
    }

    private fun addBookToFirebase() {

        val bookName = binding.edittextBookName.text.toString().trim()
        val bookPrice = binding.editTextBookPrice.text.toString().toIntOrNull() ?: 0
        val bookDescription = binding.editTextBookDescription.text.toString().trim()
        val bookCategory = binding.edittextBookCategory.text.toString().trim()
        val bookStandard = binding.edittextBookStandard.text.toString().trim()
        val bookYear = binding.editTextBookYear.text.toString().trim()
        val phoneNumber = binding.editTextMobileNumber.text.toString().toIntOrNull() ?: 0
        val image = binding.imageViewBookCover.tag?.toString() ?: ""

        // Validate inputs
        if (bookName.isEmpty() || bookDescription.isEmpty() || bookCategory.isEmpty() || bookStandard.isEmpty() || bookYear.isEmpty() || imageUri == null) {
            Toast.makeText(
                requireContext(),
                "Please fill all fields and select an image",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = System.currentTimeMillis()
        val filename = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$filename")
        storageReference.putFile(imageUri!!)
            .addOnSuccessListener {
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    val bookInfo = BookInfo(
                        bookName,
                        bookPrice,
                        bookDescription,
                        bookCategory,
                        bookStandard,
                        bookYear,
                        phoneNumber,
                        uri.toString()
                    )

                    // Push to Firebase Database
                    val bookId = databaseReference.push().key ?: return@addOnSuccessListener
                    //val bookId = "1"
                    databaseReference.child(bookId).setValue(bookInfo)
                        .addOnSuccessListener {
                            Toast.makeText(
                                requireContext(),
                                "Book added successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Clear input fields if needed
                            clearInputFields()
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                requireContext(),
                                "Failed to add book: ${it.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    requireContext(),
                    "Failed to upload image: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun clearInputFields() {
        binding.edittextBookName.text?.clear()
        binding.editTextBookPrice.text?.clear()
        binding.editTextBookDescription.text?.clear()
        binding.edittextBookCategory.text.clear()
        binding.edittextBookStandard.text.clear()
        binding.editTextBookYear.text?.clear()
        binding.editTextMobileNumber.text?.clear()
        binding.imageViewBookCover.setImageURI(null)
        imageUri = null
    }

    private fun setAutoCompleteTextView() {
        val fieldsAdapter = ArrayAdapter(
            requireContext(),
            R.layout.show_list,
            Category.fields
        )
        val onetotenAdapter = ArrayAdapter(
            requireContext(),
            R.layout.standard_showlist,
            Category.oneToTen
        )
        val eleventotwelveAdapter = ArrayAdapter(
            requireContext(),
            R.layout.standard_showlist,
            Category.elevenToTwelve
        )
        val graduationAdapter = ArrayAdapter(
            requireContext(),
            R.layout.standard_showlist,
            Category.graduation
        )
        val engineeringAdapter = ArrayAdapter(
            requireContext(),
            R.layout.standard_showlist,
            Category.engineering
        )

        // Apply the adapters to the corresponding AutoCompleteTextViews
        binding.apply {
            edittextBookCategory.setAdapter(fieldsAdapter)
            edittextBookCategory.setOnItemClickListener { parent, view, position, id ->
                val selectedCategory = fieldsAdapter.getItem(position)
                when (selectedCategory) {
                    "1 to 10" -> {
                        edittextBookStandard.setAdapter(onetotenAdapter)
                        Toast.makeText(requireContext(), "Selected: 1 to 10", Toast.LENGTH_SHORT)
                            .show()
                    }

                    "11 to 12" -> {
                        edittextBookStandard.setAdapter(eleventotwelveAdapter)
                        Toast.makeText(requireContext(), "Selected: 11 to 12", Toast.LENGTH_SHORT)
                            .show()
                    }

                    "Graduation" -> {
                        edittextBookStandard.setAdapter(graduationAdapter)
                        Toast.makeText(requireContext(), "Selected: Graduation", Toast.LENGTH_SHORT)
                            .show()
                    }

                    "Engineering" -> {
                        edittextBookStandard.setAdapter(engineeringAdapter)
                        Toast.makeText(
                            requireContext(),
                            "Selected: Engineering",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        Toast.makeText(requireContext(), "Invalid category", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
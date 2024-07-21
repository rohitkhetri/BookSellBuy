//package com.example.booksellbuy.RecyclerView_Fragment
//
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.StaggeredGridLayoutManager
//import com.example.booksellbuy.Activity.BookInfo
//import com.example.booksellbuy.Adapter.One_Adapter
//import com.example.booksellbuy.R
//import com.example.booksellbuy.databinding.FragmentRecyclerViewOneStandardBinding
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class RecyclerView_OneStandard : Fragment() {
//
//    private lateinit var binding: FragmentRecyclerViewOneStandardBinding
//    private lateinit var databaseReference: DatabaseReference
//    private lateinit var booksAdapter: One_Adapter
//    private lateinit var bookarraylist: ArrayList<BookInfo>
//    private lateinit var bookrecyclerview: RecyclerView
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding = FragmentRecyclerViewOneStandardBinding.inflate(inflater, container, false)
//
//        bookrecyclerview = binding.onestandardFragmentBooklist
//
//        bookrecyclerview.layoutManager =
//            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("books").orderByChild("1st Standard").equalTo(bookStandard)
//        //databaseReference.orderByChild("books").equalTo("1st Standard")
//
//        bookarraylist = ArrayList()
//        booksAdapter = One_Adapter(bookarraylist, requireContext())
//        bookrecyclerview.adapter = booksAdapter
//
//        getoneStandard_data()
//        return binding.root
//
//    }
//
//    private fun getoneStandard_data() {
//        //val url = "https://booksellbuy-f0319-default-rtdb.firebaseio.com/books/1st Standard"
//        //databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("books").child("1st Standard")
//        //databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(url)
//        databaseReference.orderByChild("bookStandard").equalTo("1st Standard")
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                bookarraylist.clear()
//                for (dataSnapshot in snapshot.children) {
//                    val book = dataSnapshot.getValue(BookInfo::class.java)
//                    if (book != null) {
//                        bookarraylist.add(book)
//                    }
//                    else {
//                        Log.e("FirebaseData", "DataSnapshot is not convertible to BookInfo: ${dataSnapshot.value}")
//                    }
//                }
//                booksAdapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        }
//        )
//    }
//}


package com.example.booksellbuy.RecyclerView_Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.booksellbuy.Activity.BookInfo
import com.example.booksellbuy.Adapter.Books_Adapter
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentRecyclerViewHomeBinding
import com.example.booksellbuy.databinding.FragmentRecyclerViewOneStandardBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecyclerView_OneStandard : Fragment() {

    private lateinit var binding: FragmentRecyclerViewOneStandardBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var bookrecyclerview: RecyclerView
    private lateinit var bookarraylist: ArrayList<BookInfo>
    private lateinit var booksAdapter: Books_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewOneStandardBinding.inflate(inflater, container, false)

        bookrecyclerview = binding.onestandardFragmentBooklist // Initialize bookrecyclerview using binding

        bookrecyclerview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        bookrecyclerview.setHasFixedSize(true)

        databaseReference = FirebaseDatabase.getInstance().getReference("books")

        bookarraylist = ArrayList()
        booksAdapter = Books_Adapter(bookarraylist, requireContext())
        bookrecyclerview.adapter = booksAdapter

//        // Fetch books for a specific standard
//        val bookStandard = "1st Standard"
//        getBooksByStandard(bookStandard)

        val book1stStandard = arguments?.getString(ARG_BOOK_STANDARD) ?: ""
        getBooksByStandard(book1stStandard)

//        val book2ndStandard = arguments?.getString(ARG_BOOK_STANDARD) ?: "2st Standard"
//        getBooksByStandard(book2ndStandard)

        return binding.root
    }

    private fun getBooksByStandard(bookStandard: String) {
        databaseReference.orderByChild("bookStandard").equalTo(bookStandard)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //bookarraylist.clear()
                    for (bookSnapshot in snapshot.children) {
                        val book = bookSnapshot.getValue(BookInfo::class.java)
                        if (book?.bookStandard == bookStandard) {
                            book?.let { bookarraylist.add(it) }
                        }
                    }
                    booksAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                }
            })
    }

    companion object {
        private const val ARG_BOOK_STANDARD = "bookStandard"

        fun newInstance(bookStandard: String): RecyclerView_Home {
            val fragment = RecyclerView_Home()
            val args = Bundle()
            args.putString(ARG_BOOK_STANDARD, bookStandard)
            fragment.arguments = args
            return fragment
        }
    }
}

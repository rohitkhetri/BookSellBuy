package com.example.booksellbuy.RecyclerView_Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.booksellbuy.Activity.BookInfo
import com.example.booksellbuy.Adapter.Books_Adapter
import com.example.booksellbuy.R
import com.example.booksellbuy.databinding.FragmentRecyclerViewHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.facebook.shimmer.ShimmerFrameLayout

class RecyclerView_Home : Fragment() {

    private lateinit var binding: FragmentRecyclerViewHomeBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var bookrecyclerview: RecyclerView
    private lateinit var bookarraylist: ArrayList<BookInfo>
    private lateinit var booksAdapter: Books_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewHomeBinding.inflate(inflater, container, false)

        bookrecyclerview = binding.homeFragmentBooklist // Initialize bookrecyclerview using binding

//        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
//            2, // Number of columns
//            StaggeredGridLayoutManager.VERTICAL // Orientation
//        )

        bookrecyclerview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) // Use requireContext() for context
        bookrecyclerview.setHasFixedSize(true)

        databaseReference = FirebaseDatabase.getInstance().getReference("books")

        bookarraylist = ArrayList()
        booksAdapter = Books_Adapter(bookarraylist, requireContext())
        bookrecyclerview.adapter = booksAdapter
        //bookarraylist = arrayListOf()
        getBookdata()

        // Fetch books for a specific standard
//        val bookStandard = "1st Standard"
//        getBooksByStandard(bookStandard)

//        val book2ndStandard = "2st Standard"
//        getBooksBy2ndStandard(book2ndStandard)


        return binding.root
    }

    private fun getBookdata() {
         databaseReference = FirebaseDatabase.getInstance().getReference("books")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //bookarraylist.clear()
                for (dataSnapshot in snapshot.children) {
                    val book = dataSnapshot.getValue(BookInfo::class.java)
                    if (book != null) {
                        bookarraylist.add(book)
                    }
                }
                booksAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })

    }

//    private fun getBooksByStandard(bookStandard: String) {
//        databaseReference.orderByChild("book1stStandard").equalTo(bookStandard)
//            .addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    //bookarraylist.clear()
//                    for (bookSnapshot in snapshot.children) {
//                        val book = bookSnapshot.getValue(BookInfo::class.java)
//                        if (book?.bookStandard == bookStandard) {
//                            book?.let { bookarraylist.add(it) }
//                        }
//                    }
//                    booksAdapter.notifyDataSetChanged()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    // Handle database error
//                }
//            })
//    }
}

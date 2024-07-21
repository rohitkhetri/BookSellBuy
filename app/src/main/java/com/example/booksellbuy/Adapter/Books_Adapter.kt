package com.example.booksellbuy.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksellbuy.Activity.BookInfo
import com.example.booksellbuy.databinding.BookItemsBinding

class Books_Adapter(private val booklist: ArrayList<BookInfo>, private val context: Context) :
    RecyclerView.Adapter<Books_Adapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = booklist[position]
        holder.bind(book)
    }

    inner class BookViewHolder(private val binding: BookItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookInfo) {
            binding.bookname.text = book.bookname
            binding.bookprice.text = book.bookdescription
            //binding.bookimg.text = book.imageUrl

            // Assuming BookInfo has a property 'imageUrl' for the book cover image URL
            Glide.with(context)
                .load(book.imageUrl)
                .into(binding.bookimg)
        }
    }
}


package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Filter

class Colleges : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var arrayAdapter: CustomArrayAdapter
    private lateinit var searchView: SearchView
    private lateinit var popupImage: ImageView

    private val names = arrayOf(
        "Graduate Studies",
        "Pharmacy",
        "Modern Media",
        "Modern Sciences",
        "Digital Sciences",
        "Artificial Intelligence and Data Science",
        "Allied Medical Sciences"
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colleges)

        setupUI()
        setupListView()
        setupSearchView()
        setupPopupMenu()
    }

    private fun setupUI() {
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            startActivity(Intent(this, CollegeSubject::class.java))
        }

        popupImage = findViewById(R.id.popupImage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListView() {
        listView = findViewById(R.id.listview)
        arrayAdapter = CustomArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        listView.adapter = arrayAdapter
    }

    private fun setupSearchView() {
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                arrayAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun setupPopupMenu() {
        popupImage.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.menu_item1 -> {
                        Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.menu_item2 -> {
                        Toast.makeText(this, "Info clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.menu_item3 -> {
                        Toast.makeText(this, "Report clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private class CustomArrayAdapter(context: Colleges, resource: Int, private val items: Array<String>) :
        ArrayAdapter<String>(context, resource, items) {

        private var filteredItems: List<String> = items.toList()

        override fun getCount(): Int = filteredItems.size

        override fun getItem(position: Int): String? = filteredItems[position]

        override fun getPosition(item: String?): Int = filteredItems.indexOf(item)

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val results = FilterResults()
                    val query = constraint.toString().lowercase()
                    results.values = if (query.isEmpty()) {
                        items.toList()
                    } else {
                        items.filter { it.lowercase().contains(query) }
                    }
                    return results
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    filteredItems = results?.values as List<String>
                    notifyDataSetChanged()
                }
            }
        }
    }
}
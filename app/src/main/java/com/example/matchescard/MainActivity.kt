package com.example.matchescard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchescard.room.DatabaseBuilder
import com.example.matchescard.room.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var adapter: PersonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonAdapter(this, mutableListOf())
        recyclerView.adapter = adapter
        progressBar.visibility = View.VISIBLE
        val db = DatabaseBuilder.getInstance(applicationContext).PersonDao()
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val firstLaunch = sh.getBoolean("firstLaunch",true)
        if (firstLaunch) {
            RetrofitClient.getAllItems(10).observe(this) {
                progressBar.visibility = View.GONE
                if (it.isNotEmpty()) {
                    adapter.setData(it)
                    CoroutineScope(Dispatchers.IO).launch {
                        db.insertAll(it)
                    }
                    val editor = sh.edit()
                    editor.putBoolean("firstLaunch", false)
                    editor.apply()
                } else {
                    Toast.makeText(this,"Some Network issue pls restart the app",Toast.LENGTH_LONG).show()
                }
            }
        }else{
            db.getAll().observe(this){
                progressBar.visibility = View.GONE
                adapter.setData(it)
            }
        }
    }
}
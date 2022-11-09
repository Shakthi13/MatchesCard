package com.example.matchescard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.matchescard.room.DatabaseBuilder

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra("personId",0)
        val title = findViewById<TextView>(R.id.characterName)
        val characterNo = findViewById<TextView>(R.id.characterNo)
        val characterAge = findViewById<TextView>(R.id.characterAge)
        val characterGender = findViewById<TextView>(R.id.characterGender)
        val characterAddress = findViewById<TextView>(R.id.characterAddress)
        val characterMsg = findViewById<TextView>(R.id.characterMessage)
        val characterImage = findViewById<ImageView>(R.id.characterImage)

        val db = DatabaseBuilder.getInstance(applicationContext).PersonDao()
        db.getPerson(id).observe(this){
            title.text = it.name
            characterNo.text = "Phone No: "+it.phoneNo
            characterAge.text = "Age: "+it.age
            characterGender.text = "Gender: "+it.gender
            characterAddress.text = "Address: "+it.location
            characterMsg.text = it.clickedOption
            Glide.with(this).load(it.imageUrlBig).into(characterImage)
        }
    }
}
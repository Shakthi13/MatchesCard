package com.example.matchescard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchescard.room.DatabaseBuilder
import com.example.matchescard.room.Person
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PersonAdapter(private val context:Context, private val dataList:MutableList<Person>):RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    inner class PersonViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val personAge: TextView = itemView.findViewById(R.id.age)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val message: TextView = itemView.findViewById(R.id.message)
        val location: TextView = itemView.findViewById(R.id.location)
        val image: ImageView = itemView.findViewById(R.id.image)
        val buttonLayout:LinearLayout = itemView.findViewById(R.id.buttons)
        lateinit var item:Person
        var id:Int = 0
        init {
            val accept: TextView = itemView.findViewById(R.id.accept)
            val decline: TextView = itemView.findViewById(R.id.decline)
            itemView.setOnClickListener {
                val intent = Intent(context,DetailActivity::class.java)
                intent.putExtra("personId",id)
                context.startActivity(intent)
            }
            accept.setOnClickListener {
                val db = DatabaseBuilder.getInstance(context.applicationContext).PersonDao()
                item.clickedOption = "Member Accepted"
                notifyDataSetChanged()
                GlobalScope.launch { db.update(item) }

            }
            decline.setOnClickListener {
                val db = DatabaseBuilder.getInstance(context.applicationContext).PersonDao()
                item.clickedOption = "Member Declined"
                notifyDataSetChanged()
                GlobalScope.launch { db.update(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.info_card,parent,false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = dataList[position]
        holder.apply {
            item = person
            id = person.id
            name.text = person.name
            personAge.text = "Age: "+person.age.toString()
            gender.text = person.gender
            location.text = person.location
            Glide.with(context).load(person.imageUrl).into(image)
            if (person.clickedOption.isEmpty()) {
                buttonLayout.visibility = View.VISIBLE
                message.visibility = View.GONE
            } else {
                message.text = person.clickedOption
                buttonLayout.visibility = View.GONE
                message.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list:List<Person>){
        this.dataList.addAll(list)
        notifyDataSetChanged()
    }
}
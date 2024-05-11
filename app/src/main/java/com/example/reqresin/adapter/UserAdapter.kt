package com.example.reqresin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reqresin.DetailUser
import com.example.reqresin.MainActivity
import com.example.reqresin.R
import com.example.reqresin.model.DataItem

class UserAdapter(val data : List<DataItem?>?) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.txt_name)
        val email = view.findViewById<TextView>(R.id.txt_email)
        val image = view.findViewById<ImageView>(R.id.img_user)
        val item = view.findViewById<LinearLayout>(R.id.linear_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = data?.get(position)?.firstName + " " + data?.get(position)?.lastName
        holder.email.text = data?.get(position)?.email
        Glide.with(holder.image)
            .load(data?.get(position)?.avatar)
            .error(R.drawable.ic_launcher_background)
            .into(holder.image)
        holder.item.setOnClickListener {
            if(data?.get(position)?.id != null){
                val intent = Intent(holder.itemView.context,DetailUser::class.java)
                intent.putExtra("id",data?.get(position)?.id)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        if(data != null){
            return data.size
        }
        return 0
    }
}
package com.example.mvvm.Ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.model.Comment

class MainAdapter(val commentList:ArrayList<Comment>):RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
       val comment=commentList[position]
        holder.body.text=comment.body
        holder.name.text=comment.name
    }

    override fun getItemCount():Int= commentList.size


    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name=itemView.findViewById<TextView>(R.id.textView_name)
        var body=itemView.findViewById<TextView>(R.id.textView_body)
    }

}
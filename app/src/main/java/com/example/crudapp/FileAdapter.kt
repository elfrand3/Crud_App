package com.example.crudapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.crudapp.model.DataItem


class FileAdapter (private var listData:  ArrayList<DataItem>):
    RecyclerView.Adapter<FileAdapter.myviewHolder>() {

    private lateinit var aClick : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener:onItemClickListener){
        aClick = listener
    }

    class myviewHolder (itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val id : TextView = itemView.findViewById(R.id.iv_id)
        val fn : TextView = itemView.findViewById(R.id.iv_first_name)
        val ln : TextView = itemView.findViewById(R.id.iv_last_name)
        val em : TextView = itemView.findViewById(R.id.iv_email)
        val av : ImageView = itemView.findViewById(R.id.iv_avatar)
        val up : ImageButton = itemView.findViewById(R.id.id_update)
        val dl : ImageButton = itemView.findViewById(R.id.id_delete)


        init {
            up.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
            dl.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_data,parent,false)
        return myviewHolder(view, aClick)


    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {
        val data = listData[position]
        holder.id.text = data.id.toString()
        holder.fn.text = data.firstName
        holder.ln.text = data.lastName
        holder.em.text = data.email
        Glide.with(holder.itemView)
            .load("${data.avatar}")
            .apply (RequestOptions.overrideOf(150,150)).into(holder.av)
    }

    override fun getItemCount() = listData.size

    fun getData(data: List<DataItem>){
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
}
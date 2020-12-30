package com.example.internshipproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internshipproject.R
import com.example.internshipproject.model.Post


class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){

    private val title: TextView = v.findViewById(R.id.title_tv)
    private val body: TextView = v.findViewById(R.id.body_tv)



    fun initialize(post: Post, action: OnCellClickListener){
        title.text = post.title
        body.text = post.body
        itemView.setOnClickListener{
            action.onCellClickListener(post, adapterPosition)
        }

    }
}

interface OnCellClickListener {
    fun onCellClickListener(item: Post, position: Int)
}

class RecyclerViewAdapter(private var listOfPosts: List<Post>, private var clickListener: OnCellClickListener) : RecyclerView.Adapter<MyViewHolder>()  {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_view, parent, false)
        return MyViewHolder(view)    }

    override fun getItemCount(): Int {
    return listOfPosts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initialize(listOfPosts.get(position), clickListener)
    }

    fun setDataToAdapter(listOfPosts: List<Post> ){
        this.listOfPosts = listOfPosts
        notifyDataSetChanged()
    }

    fun deleteItem(pos: Int){
        listOfPosts.toArrayList().removeAt(pos)
        notifyItemRemoved(pos)
    }

    private fun <T> List<T>.toArrayList(): ArrayList<T>{
        return ArrayList(this)
    }





}
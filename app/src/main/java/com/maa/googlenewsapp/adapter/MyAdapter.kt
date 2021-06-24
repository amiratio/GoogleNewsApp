package com.maa.googlenewsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.maa.googlenewsapp.R
import com.maa.googlenewsapp.model.News
import com.squareup.picasso.Picasso

class MyAdapter(var itemList: ArrayList<News>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.frame.shapeAppearanceModel= ShapeAppearanceModel().toBuilder().setAllCorners(CornerFamily.ROUNDED, 20F).build()
        holder.image.shapeAppearanceModel= ShapeAppearanceModel().toBuilder().setTopLeftCorner(CornerFamily.ROUNDED, 20F).
                setTopRightCorner(CornerFamily.ROUNDED, 20F).build()
        Picasso.get().load(itemList[position].urlToImage).into(holder.image)
        holder.title.text= itemList[position].title
        holder.description.text= itemList[position].description
        holder.date.text= itemList[position].publishedAt
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView){
        val frame: ShapeableImageView
        val image: ShapeableImageView
        val title: TextView
        val description: TextView
        val date: TextView

        init {
            frame= itemView.findViewById(R.id.shapeable1)
            image= itemView.findViewById(R.id.shapeable2)
            title= itemView.findViewById(R.id.news_title)
            description= itemView.findViewById(R.id.news_description)
            date= itemView.findViewById(R.id.news_date)
        }
    }
}
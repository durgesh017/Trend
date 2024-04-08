package com.example.trendteller.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trendteller.R
import com.example.trendteller.model.ArticlesItem


class NewsAdapter(val activity: Context?, var Data: List<ArticlesItem?>?) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.newsview, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.headlineTxt.text = Data!!.get(position)!!.title
        holder.descriptionTxt.text = Data!!.get(position)!!.description
        Glide.with(activity!!).load(Data!![position]?.urlToImage).into(holder.imageView)

        holder.cardView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Data!!.get(position)!!.url))
            activity.startActivity(browserIntent)

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var headlineTxt = itemView.findViewById<TextView>(R.id.titleTxt)
        var descriptionTxt = itemView.findViewById<TextView>(R.id.descriptionTxt)
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
        var cardView = itemView.findViewById<CardView>(R.id.cardView)

    }


}
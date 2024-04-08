package com.example.trendteller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.trendteller.R

class InfoAdapter(
    val activity: FragmentActivity?,
    val steps: Array<String>,
    val positions: Array<Int>
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.infoview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return positions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.infoTxt.text = steps.get(position).toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var infoTxt = itemView.findViewById<TextView>(R.id.worksTxt)

    }

}
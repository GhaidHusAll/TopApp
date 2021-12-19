package com.example.topapplications

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topapplications.databinding.RowBinding
import com.squareup.picasso.Picasso

class AdapterApp(private val list: ArrayList<Application>, val activity: Activity):RecyclerView.Adapter<AdapterApp.HolderApp>() {
    class HolderApp(val Binding: RowBinding):RecyclerView.ViewHolder(Binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderApp {
       return HolderApp(RowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HolderApp, position: Int) {
        val item = list[position]
        holder.Binding.apply {
            tvApps.text = item.name
            Picasso.get().load(item.url).into(ivImage)
        }
        holder.Binding.llRow.setOnClickListener {
            (activity as MainActivity).toDisplay(item)
        }
    }

    override fun getItemCount() = list.size
}
package com.example.retrofitandrxjava

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitandrxjava.databinding.RowLayoutBinding


class MyAdapter (private val dataList : ArrayList<MyData.MyDataItem>) :  RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(var binding: RowLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.item=dataList.get(position)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

}
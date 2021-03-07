package com.example.calculatesamples.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatesamples.data.RowVariationSeries
import com.example.calculatesamples.databinding.ItemVariationSeriesBinding

class VariationsSeriesAdapter(
    private val listVariationsSeriesAdapter: List<RowVariationSeries>
) :
    RecyclerView.Adapter<VariationsSeriesAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemBinding = ItemVariationSeriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RowViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        holder.bind(listVariationsSeriesAdapter[position])
    }

    override fun getItemCount(): Int = listVariationsSeriesAdapter.size


    class RowViewHolder(itemBinding: ItemVariationSeriesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(rowVariationSeries: RowVariationSeries) {

        }
    }

}
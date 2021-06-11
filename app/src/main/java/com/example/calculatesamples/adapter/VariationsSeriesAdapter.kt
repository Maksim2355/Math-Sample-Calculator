package com.example.calculatesamples.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatesamples.R
import com.example.calculatesamples.data.RowVariationSeries
import com.example.calculatesamples.databinding.ItemVariationSeriesBinding

class VariationsSeriesAdapter(
    private val listVariationsSeriesAdapter: List<RowVariationSeries>
) : RecyclerView.Adapter<VariationsSeriesAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemBinding = ItemVariationSeriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RowViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        if (position == 0){
            holder.bind()
        }else {
            holder.bind(listVariationsSeriesAdapter[position - 1])
        }
    }

    override fun getItemCount(): Int = listVariationsSeriesAdapter.size


    class RowViewHolder(private val itemBinding: ItemVariationSeriesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(){
            with(itemBinding) {
                valueTv.text = "Xi"
                frequencyTv.text = "Ni"
                relativeFrequencyTv.text = "Ni/N"
                accumulatedFrequencyTv.text = "Накопленная частость"
            }
        }

        fun bind(rowVariationSeries: RowVariationSeries) {
            with(itemBinding) {
                valueTv.text =
                    if (rowVariationSeries.range.max - rowVariationSeries.range.min == 1) {
                        rowVariationSeries.range.min.toString()
                    } else "${rowVariationSeries.range.min}-${rowVariationSeries.range.max}"
                frequencyTv.text = rowVariationSeries.frequency.toString()
                relativeFrequencyTv.text = rowVariationSeries.relativeFrequency.toString()
                accumulatedFrequencyTv.text = rowVariationSeries.accumulatedFrequency.toString()
            }
        }
    }

}
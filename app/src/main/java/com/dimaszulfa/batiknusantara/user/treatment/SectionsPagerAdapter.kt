package com.dimaszulfa.batiknusantara.user.treatment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimaszulfa.batiknusantara.data.TreatmentEntity
import com.dimaszulfa.batiknusantara.databinding.ItemIntroductionBinding
import com.dimaszulfa.batiknusantara.databinding.ItemTreatmentBinding

class SectionPagerAdapter(private val context: Context, private var listData: ArrayList<TreatmentEntity>): RecyclerView.Adapter<SectionPagerAdapter.Pager2ViewHolder>() {
    inner class Pager2ViewHolder(binding: ItemTreatmentBinding): RecyclerView.ViewHolder(binding.root){
        val step = binding.txTitleContainer
        val image = binding.ivImageExample
        val stepdesc = binding.txStepDesc


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val view = ItemTreatmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Pager2ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.step.text = listData[position].step
        Glide.with(context).load(listData[position].image).into(holder.image)
        holder.stepdesc.text = listData[position].step_desc
    }

    override fun getItemCount(): Int = listData.size


}
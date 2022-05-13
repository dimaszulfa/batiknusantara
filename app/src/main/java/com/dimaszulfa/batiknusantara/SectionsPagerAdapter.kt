package com.dimaszulfa.batiknusantara

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dimaszulfa.batiknusantara.databinding.ItemIntroductionBinding

class SectionPagerAdapter(private var title: List<String>, private var content: List<String>): RecyclerView.Adapter<SectionPagerAdapter.Pager2ViewHolder>() {
    inner class Pager2ViewHolder(binding: ItemIntroductionBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.txTitleContainer
        val content = binding.txContent


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val view = ItemIntroductionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Pager2ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.title.text = title[position]
        holder.content.text = content[position]
    }

    override fun getItemCount(): Int = title.size


}
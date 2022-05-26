package com.dimaszulfa.batiknusantara.motive

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimaszulfa.batiknusantara.data.MotiveEntity
import com.dimaszulfa.batiknusantara.databinding.ItemUserMotiveBinding

class UserMotiveAdapter(private val context: Context, private val data: ArrayList<MotiveEntity>) :
    RecyclerView.Adapter<UserMotiveAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemUserMotiveBinding) : RecyclerView.ViewHolder(binding.root) {
        var title = binding.txTitle
        var image = binding.ivImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserMotiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = data[position]
        with(holder) {
            holder.title.text = list.title
            Glide.with(context).load(list.image).into(holder.image)
        }
    }

    override fun getItemCount(): Int = data.size

}
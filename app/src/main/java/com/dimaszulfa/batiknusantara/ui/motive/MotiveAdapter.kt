package com.dimaszulfa.batiknusantara.ui.motive

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.MotiveEntity
import com.dimaszulfa.batiknusantara.databinding.ItemUserMotiveBinding

class MotiveAdapter(private val context: Context, private val data: ArrayList<MotiveEntity>) : RecyclerView.Adapter<MotiveAdapter.ViewHolder>() {

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
        val bundle = Bundle().apply {
            putString("title", list.title)
            putString("description", list.description)
            putString("image", list.image)
            putString("province", list.province)
            putString("category", list.category)
        }
        with(holder) {
            title.text = list.title
            Glide.with(context).load(list.image).into(image)
            holder.image.setOnClickListener {
                goToUserMotiveDetail(it, bundle)
            }
        }
    }

    private fun goToUserMotiveDetail(view: View, bundle: Bundle) {
        view.findNavController().navigate(R.id.action_userMotiveFragment_to_userMotiveDetail, bundle)
    }

    override fun getItemCount(): Int = data.size

}
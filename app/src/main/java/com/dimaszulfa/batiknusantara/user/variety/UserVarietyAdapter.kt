package com.dimaszulfa.batiknusantara.user.variety

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimaszulfa.batiknusantara.data.VarietyEntity
import com.dimaszulfa.batiknusantara.databinding.ItemUserVarietyBinding

class UserVarietyAdapter(val variety: ArrayList<VarietyEntity>) :
    RecyclerView.Adapter<UserVarietyAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserVarietyBinding) : RecyclerView.ViewHolder(binding.root) {

        var title = binding.txExpandTitle
        var desc = binding.txExpandDesc
        var expandableLayout = binding.expandLayout
        var linearLayout = binding.linearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemUserVarietyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = variety[position]

        with(holder) {
            title.text = list.title
            desc.text = list.desc
            expandableLayout.visibility = if (list.isExpand!!) View.VISIBLE else View.GONE
            linearLayout.setOnClickListener {
                list.isExpand = !list.isExpand!!
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = variety.size


}
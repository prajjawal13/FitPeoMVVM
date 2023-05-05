package com.fitfeo.demo.adapter

import android.content.Context
import android.content.Intent
import android.telecom.Call.Details
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fitfeo.demo.R
import com.fitfeo.demo.activity.ImageDetailActivity
import com.fitfeo.demo.adapter.ImageAdapter.ImageViewHolder
import com.fitfeo.demo.databinding.ItemLayoutBinding
import com.fitfeo.demo.model.ImageModel
import com.squareup.picasso.Picasso

/**
 * This Adapter holds the data with recylerView
 *  @param: contains the context of the data
 */
class ImageAdapter(var context: Context) : RecyclerView.Adapter<ImageViewHolder>(){

    var list = mutableListOf<ImageModel>()

    /**
     *  Returns and binds the data with ViewHolder
     */
    class ImageViewHolder(val binding: ItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(imageModel: ImageModel) {
            binding.imagemodel = imageModel
            binding.tvDescription.text=imageModel.title
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ImageViewHolder(binding)
            }
        }
    }

    /**
     * create the ViewHolder and return the parent of the view with ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent) as ImageViewHolder
    }

    /**
     *  Binds the data with ViewHolder
     */
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val rickMorty=list[position]
        holder.bind(rickMorty)

        holder.binding.apply {
            holder.itemView.apply {
                tvDescription.text = "${rickMorty.title}"
                Picasso.get().load(list[position].url).into(holder.binding.imageView)
            }
        }
        holder.itemView.setOnClickListener {
            val mainIntent = Intent(context, ImageDetailActivity::class.java)
            mainIntent.putExtra("imageurl", rickMorty.url)
            mainIntent.putExtra("title", rickMorty.title)
            context.startActivity(mainIntent)
        }
    }

    /**
     *  Set the list of data
     */
    fun setDataList(list: List<ImageModel>) {
        this.list =list.toMutableList()
        notifyDataSetChanged()
    }

    /**
     *  Returns the list size
     */
    override fun getItemCount(): Int {
        return list.size
    }
}
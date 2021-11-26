package com.rexoit.al_arabia_darus_sunnah_madrasha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.adapter.MainActivityAdapter.MainActivityViewHolder
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.MainRecModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick

class MainActivityAdapter : ListAdapter<MainRecModel, MainActivityViewHolder>(DIFF_UTIL_CALL_BACK) {
	
	var recyclerViewItemClick: RecyclerViewItemClick? = null
	
	fun onRecyclerItemClick(recyclerViewItemClick: RecyclerViewItemClick) {
		this.recyclerViewItemClick = recyclerViewItemClick
	}
	
	class MainActivityViewHolder(
		itemView: View,
		private val recyclerViewItemClick: RecyclerViewItemClick
	) :
		RecyclerView.ViewHolder(itemView) {
		private val image: ImageView = itemView.findViewById(R.id.sample_main_image)
		private val name: TextView = itemView.findViewById(R.id.sample_name)
		fun bind(mainRecModel: MainRecModel) {
			Glide.with(itemView).load(mainRecModel.image).into(image)
			name.text = mainRecModel.name
			itemView.setOnClickListener {
				recyclerViewItemClick.let {
					if (adapterPosition != RecyclerView.NO_POSITION) {
						it.onItemClick(adapterPosition, itemView)
					}
				}
			}
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.main_rec_row, parent, false)
		return MainActivityViewHolder(view, recyclerViewItemClick!!)
	}
	
	override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
		val mainRecModel = getItem(position)
		holder.bind(mainRecModel)
	}
	
	companion object {
		val DIFF_UTIL_CALL_BACK = object : DiffUtil.ItemCallback<MainRecModel>() {
			override fun areItemsTheSame(oldItem: MainRecModel, newItem: MainRecModel): Boolean {
				return oldItem.image == newItem.image && oldItem.name == newItem.name
			}
			
			override fun areContentsTheSame(oldItem: MainRecModel, newItem: MainRecModel): Boolean {
				return oldItem.image == newItem.image && oldItem.name == newItem.name
			}
			
		}
	}
}
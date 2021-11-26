package com.rexoit.al_arabia_darus_sunnah_madrasha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.ClassNameModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick

class ClassNameAdapter :
	ListAdapter<ClassNameModel, ClassNameAdapter.StudentInformationHolder>(
		DIFF_UTIL_CALL_BACK
	) {
	
	var recyclerViewItemClick: RecyclerViewItemClick? = null
	
	fun onItemClick(recyclerViewItemClick: RecyclerViewItemClick) {
		this.recyclerViewItemClick = recyclerViewItemClick
	}
	
	class StudentInformationHolder(
		itemView: View,
		private val recyclerViewItemClick: RecyclerViewItemClick
	) : RecyclerView.ViewHolder(itemView) {
		private val className: TextView = itemView.findViewById(R.id.sample_class_text)
		
		fun bind(classNameModel: ClassNameModel) {
			className.text = classNameModel.className
			itemView.setOnClickListener {
				if (adapterPosition != RecyclerView.NO_POSITION) {
					recyclerViewItemClick.onItemClick(adapterPosition, itemView)
				}
			}
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentInformationHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.class_rec_row, parent, false)
		return StudentInformationHolder(view, recyclerViewItemClick!!)
	}
	
	override fun onBindViewHolder(holder: StudentInformationHolder, position: Int) {
		val classNameModel = getItem(position)
		holder.bind(classNameModel = classNameModel)
		
	}
	
	companion object {
		val DIFF_UTIL_CALL_BACK = object : DiffUtil.ItemCallback<ClassNameModel>() {
			override fun areItemsTheSame(
				oldItem: ClassNameModel,
				newItem: ClassNameModel
			): Boolean {
				return oldItem.className == newItem.className
			}
			
			override fun areContentsTheSame(
				oldItem: ClassNameModel,
				newItem: ClassNameModel
			): Boolean {
				return oldItem.className == newItem.className
			}
		}
	}
}
package com.rexoit.al_arabia_darus_sunnah_madrasha.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.StudentListModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick
import com.squareup.picasso.Picasso

class StudentListAdapter : ListAdapter<StudentListModel, StudentListAdapter.StudentListHolder>(
	DIFF_UTIL_CALL_BACK
) {
	
	var recyclerViewItemClick: RecyclerViewItemClick? = null
	
	fun onItemClick(recyclerViewItemClick: RecyclerViewItemClick) {
		this.recyclerViewItemClick = recyclerViewItemClick
	}
	
	class StudentListHolder(itemView: View, val recyclerViewItemClick: RecyclerViewItemClick) :
		RecyclerView.ViewHolder(itemView) {
		val sample_student_list_image: ImageView =
			itemView.findViewById(R.id.sample_student_list_image)
		val sample_student_list_name: TextView =
			itemView.findViewById(R.id.sample_student_list_name)
		val sample_student_list_father: TextView =
			itemView.findViewById(R.id.sample_student_list_father)
		val sample_student_list_roll: TextView =
			itemView.findViewById(R.id.sample_student_list_roll)
		val sample_student_list_age: TextView = itemView.findViewById(R.id.sample_student_list_age)
		val sample_student_list_mother: TextView =
			itemView.findViewById(R.id.sample_student_list_mother)
		val sample_student_list_current_address: TextView =
			itemView.findViewById(R.id.sample_student_list_current_address)
		val sample_student_list_district: TextView =
			itemView.findViewById(R.id.sample_student_list_district)
		
		fun bind(studentListModel: StudentListModel) {
			Picasso.get().load(studentListModel.studentImage).placeholder(R.drawable.avatar)
				.into(sample_student_list_image)
			sample_student_list_name.text = studentListModel.studentName
			sample_student_list_father.text = studentListModel.studentFatherName
			sample_student_list_roll.text = studentListModel.studentRoll.toString()
			sample_student_list_age.text = studentListModel.studentAge.toString()
			sample_student_list_mother.text = studentListModel.studentMotherName
			sample_student_list_current_address.text = studentListModel.studentCurrentAddress
			sample_student_list_district.text = studentListModel.studentHomeDistrict
			
			itemView.setOnClickListener {
				if (adapterPosition != RecyclerView.NO_POSITION) {
					recyclerViewItemClick.onItemClick(adapterPosition, itemView)
				}
			}
			
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.student_list_rec_row, parent, false)
		return StudentListHolder(view, recyclerViewItemClick!!)
	}
	
	override fun onBindViewHolder(holder: StudentListHolder, position: Int) {
		val studentListModel = getItem(position)
		holder.bind(studentListModel)
	}
	
	companion object {
		val DIFF_UTIL_CALL_BACK = object : DiffUtil.ItemCallback<StudentListModel>() {
			override fun areItemsTheSame(
				oldItem: StudentListModel,
				newItem: StudentListModel
			): Boolean {
				return oldItem.studentName == newItem.studentName &&
						oldItem.studentAge == newItem.studentAge &&
						oldItem.studentImage == newItem.studentImage &&
						oldItem.studentRoll == newItem.studentRoll &&
						oldItem.studentFatherName == newItem.studentFatherName &&
						oldItem.studentMotherName == newItem.studentMotherName &&
						oldItem.studentHomeDistrict == newItem.studentHomeDistrict &&
						oldItem.studentCurrentAddress == newItem.studentCurrentAddress
			}
			
			override fun areContentsTheSame(
				oldItem: StudentListModel,
				newItem: StudentListModel
			): Boolean {
				return oldItem.studentName == newItem.studentName &&
						oldItem.studentAge == newItem.studentAge &&
						oldItem.studentImage == newItem.studentImage &&
						oldItem.studentRoll == newItem.studentRoll &&
						oldItem.studentFatherName == newItem.studentFatherName &&
						oldItem.studentMotherName == newItem.studentMotherName &&
						oldItem.studentHomeDistrict == newItem.studentHomeDistrict &&
						oldItem.studentCurrentAddress == newItem.studentCurrentAddress
			}
			
		}
	}
	
}
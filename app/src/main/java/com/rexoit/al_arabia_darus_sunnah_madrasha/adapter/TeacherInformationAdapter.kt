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
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.TeacherInformationModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick
import com.squareup.picasso.Picasso

class TeacherInformationAdapter :
	ListAdapter<TeacherInformationModel, TeacherInformationAdapter.TeacherInformationHolder>(
		DIFF_UTIL_CALL_BACK
	) {
	
	private var recyclerViewItemClick: RecyclerViewItemClick? = null
	fun onItemCLick(recyclerViewItemClick: RecyclerViewItemClick) {
		this.recyclerViewItemClick = recyclerViewItemClick
	}
	
	class TeacherInformationHolder(
		itemView: View,
		private val recyclerViewItemClick: RecyclerViewItemClick
	) : RecyclerView.ViewHolder(itemView) {
		private val teacherImage: ImageView = itemView.findViewById(R.id.sample_teacher_image)
		private val name: TextView = itemView.findViewById(R.id.sample_teacher_name)
		private val post: TextView = itemView.findViewById(R.id.sample_teacher_post)
		private val age: TextView = itemView.findViewById(R.id.sample_teacher_age)
		private val mobile: TextView = itemView.findViewById(R.id.sample_teacher_mobile)
		private val education: TextView = itemView.findViewById(R.id.sample_teacher_education)
		private val industry: TextView = itemView.findViewById(R.id.sample_teacher_industry)
		private val joinDate: TextView = itemView.findViewById(R.id.sample_teacher_joinDate)
		private val birthDate: TextView = itemView.findViewById(R.id.sample_teacher_birthDate)
		private val birthCertificateNo: TextView =
			itemView.findViewById(R.id.sample_teacher_birthCertificateNo)
		private val nId: TextView = itemView.findViewById(R.id.sample_teacher_nId)
		private val bloodGroup: TextView = itemView.findViewById(R.id.sample_teacher_bloodGroup)
		private val fbId: TextView = itemView.findViewById(R.id.sample_teacher_fbId)
		private val emailId: TextView = itemView.findViewById(R.id.sample_teacher_emailId)
		private val fatherName: TextView = itemView.findViewById(R.id.sample_teacher_fatherName)
		private val fatherMobile: TextView = itemView.findViewById(R.id.sample_teacher_fatherMobile)
		private val fatherNid: TextView = itemView.findViewById(R.id.sample_teacher_fatherNid)
		private val fatherBirthCertificateNo: TextView =
			itemView.findViewById(R.id.sample_teacher_fatherBirthCertificateNo)
		private val motherName: TextView = itemView.findViewById(R.id.sample_teacher_motherName)
		private val motherMobile: TextView = itemView.findViewById(R.id.sample_teacher_motherMobile)
		private val motherNid: TextView = itemView.findViewById(R.id.sample_teacher_motherNid)
		private val motherBirthCertificateNo: TextView =
			itemView.findViewById(R.id.sample_teacher_motherBirthCertificateNo)
		private val permanentAddress: TextView =
			itemView.findViewById(R.id.sample_teacher_permanentAddress)
		private val currentAddress: TextView =
			itemView.findViewById(R.id.sample_teacher_currentAddress)
		
		fun bind(teacherInformationModel: TeacherInformationModel) {
			Picasso.get().load(teacherInformationModel.teacherImage).placeholder(R.drawable.avatar)
				.into(teacherImage)
			name.text = teacherInformationModel.name
			post.text = teacherInformationModel.post
			age.text = teacherInformationModel.age
			mobile.text = teacherInformationModel.mobile
			education.text = teacherInformationModel.education
			industry.text = teacherInformationModel.industry
			joinDate.text = teacherInformationModel.joinDate
			birthDate.text = teacherInformationModel.birthDate
			birthCertificateNo.text = teacherInformationModel.birthCertificateNo
			nId.text = teacherInformationModel.nId
			bloodGroup.text = teacherInformationModel.bloodGroup
			fbId.text = teacherInformationModel.fbId
			emailId.text = teacherInformationModel.emailId
			fatherName.text = teacherInformationModel.fatherName
			fatherMobile.text = teacherInformationModel.fatherMobile
			fatherNid.text = teacherInformationModel.fatherNid
			fatherBirthCertificateNo.text = teacherInformationModel.fatherBirthCertificateNo
			motherName.text = teacherInformationModel.motherName
			motherMobile.text = teacherInformationModel.motherMobile
			motherNid.text = teacherInformationModel.motherNid
			motherBirthCertificateNo.text = teacherInformationModel.motherBirthCertificateNo
			permanentAddress.text = teacherInformationModel.permanentAddress
			currentAddress.text = teacherInformationModel.currentAddress
			
			itemView.setOnClickListener {
				if (adapterPosition != RecyclerView.NO_POSITION) {
					recyclerViewItemClick.onItemClick(adapterPosition, itemView)
				}
			}
			
		}
		
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherInformationHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.teacher_information_rec_row, parent, false)
		return TeacherInformationHolder(view, recyclerViewItemClick!!)
	}
	
	override fun onBindViewHolder(holder: TeacherInformationHolder, position: Int) {
		val teacherInformationModel = getItem(position)
		holder.bind(teacherInformationModel)
	}
	
	companion object {
		val DIFF_UTIL_CALL_BACK = object : DiffUtil.ItemCallback<TeacherInformationModel>() {
			override fun areItemsTheSame(
				oldItem: TeacherInformationModel,
				newItem: TeacherInformationModel
			): Boolean {
				return oldItem.name == newItem.name && oldItem.age == newItem.age && oldItem.mobile == newItem.mobile && oldItem.education == newItem.education && oldItem.industry == newItem.industry && oldItem.post == newItem.post
			}
			
			override fun areContentsTheSame(
				oldItem: TeacherInformationModel,
				newItem: TeacherInformationModel
			): Boolean {
				return oldItem.name == newItem.name && oldItem.age == newItem.age && oldItem.mobile == newItem.mobile && oldItem.education == newItem.education && oldItem.industry == newItem.industry && oldItem.post == newItem.post
			}
			
		}
	}
}
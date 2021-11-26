package com.rexoit.al_arabia_darus_sunnah_madrasha.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.adapter.TeacherInformationAdapter
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.TeacherInformationModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick
import com.rexoit.al_arabia_darus_sunnah_madrasha.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_teacher_information.*

private const val TAG = "teacherInformationActivity"

class TeacherInformationActivity : AppCompatActivity() {
	private lateinit var viewModel: ViewModel
	private lateinit var arrayList: ArrayList<TeacherInformationModel>
	private lateinit var firebaseDatabase: FirebaseDatabase
	private lateinit var reference: DatabaseReference
	private lateinit var teacherInformationAdapter: TeacherInformationAdapter
	
	@SuppressLint("LongLogTag")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_teacher_information)
		
		val toolbar = findViewById<Toolbar>(R.id.toolbar_teacher_inf)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		teacherInformationAdapter = TeacherInformationAdapter()

//		viewModel = ViewModelProvider(this)[ViewModel::class.java]
//		viewModel.getTeacherInformation()
//		viewModel.teacherInformation.observe(this, Observer {
//			teacherInformationAdapter.submitList(listOf(it))
//		})
		
		teacherInformationAdapter.submitList(teacherInformation())
		teacherInformationAdapter.onItemCLick(object : RecyclerViewItemClick {
			override fun onItemClick(position: Int, view: View) {
				val intent =
					Intent(this@TeacherInformationActivity, TeacherDetailsActivity::class.java)
				intent.apply {
					putExtra("image", arrayList[position].teacherImage)
					putExtra("name", arrayList[position].name)
					putExtra("age", arrayList[position].age)
					putExtra("mobile", arrayList[position].mobile)
					putExtra("education", arrayList[position].education)
					putExtra("industry", arrayList[position].industry)
					putExtra("post", arrayList[position].post)
					putExtra("joinDate", arrayList[position].joinDate)
					putExtra("birthDate", arrayList[position].birthDate)
					putExtra("birthCertificateNo", arrayList[position].birthCertificateNo)
					putExtra("nId", arrayList[position].nId)
					putExtra("bloodGroup", arrayList[position].bloodGroup)
					putExtra("fbId", arrayList[position].fbId)
					putExtra("emailId", arrayList[position].emailId)
					putExtra("fatherName", arrayList[position].fatherName)
					putExtra("fatherMobile", arrayList[position].fatherMobile)
					putExtra("fatherNid", arrayList[position].fatherNid)
					putExtra(
						"fatherBirthCertificateNo",
						arrayList[position].fatherBirthCertificateNo
					)
					putExtra("motherName", arrayList[position].motherName)
					putExtra("motherMobile", arrayList[position].motherMobile)
					putExtra("motherNid", arrayList[position].motherNid)
					putExtra(
						"motherBirthCertificateNo",
						arrayList[position].motherBirthCertificateNo
					)
					putExtra("permanentAddress", arrayList[position].permanentAddress)
					putExtra("currentAddress", arrayList[position].currentAddress)
				}
				startActivity(intent)
			}
		})
		rec_teacher_inf.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@TeacherInformationActivity)
			adapter = teacherInformationAdapter
		}
	}
	
	private fun teacherInformation(): ArrayList<TeacherInformationModel> {
		arrayList = ArrayList()
		firebaseDatabase = FirebaseDatabase.getInstance()
		reference = firebaseDatabase.reference.child("TeacherInformation")
		
		val teacherInformation = object : ValueEventListener {
			@SuppressLint("NotifyDataSetChanged")
			override fun onDataChange(snapshot: DataSnapshot) {
				arrayList.clear()
				if (snapshot.exists()) {
					for (dataSnapshot in snapshot.children) {
						try {
							val teacherInformationModel =
								dataSnapshot.getValue(TeacherInformationModel::class.java)
							arrayList.add(teacherInformationModel!!)
						} catch (e: Exception) {
							Log.d(TAG, "onDataChange: $e")
						}
						
					}
				}
				teacherInformationAdapter.notifyDataSetChanged()
			}
			
			@SuppressLint("LongLogTag")
			override fun onCancelled(error: DatabaseError) {
				Log.d(TAG, "onCancelled: ${error.message}")
			}
		}
		reference.addValueEventListener(teacherInformation)
		return arrayList
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			android.R.id.home -> {
				super.onBackPressed()
			}
		}
		return super.onOptionsItemSelected(item)
	}
	
}
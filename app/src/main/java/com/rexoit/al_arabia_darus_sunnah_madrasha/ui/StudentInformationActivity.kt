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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.adapter.ClassNameAdapter
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.ClassNameModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.StudentListModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.android.synthetic.main.activity_student_information.*

private const val TAG = "studentInformationActivity"

class StudentInformationActivity : AppCompatActivity() {
	private lateinit var classNameAdapter: ClassNameAdapter
	private lateinit var arrayList: ArrayList<ClassNameModel>
	
	private lateinit var childList: ArrayList<StudentListModel>
	private lateinit var nurseryList: ArrayList<StudentListModel>
	private lateinit var classOne: ArrayList<StudentListModel>
	private lateinit var classTwo: ArrayList<StudentListModel>
	private lateinit var classThree: ArrayList<StudentListModel>
	private lateinit var classFour: ArrayList<StudentListModel>
	private lateinit var classFive: ArrayList<StudentListModel>
	
	@SuppressLint("LongLogTag")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_student_information)
		
		val toolbar = findViewById<Toolbar>(R.id.toolbar_student_inf)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		classNameAdapter = ClassNameAdapter()
		classNameAdapter.submitList(getClass())
		
		
		nurseryList = ArrayList()
		val firebaseDatabase = FirebaseDatabase.getInstance()
		val reference = firebaseDatabase.reference.child("Nursery")
		reference.addValueEventListener(object : ValueEventListener {
			@SuppressLint("NotifyDataSetChanged", "LongLogTag")
			override fun onDataChange(snapshot: DataSnapshot) {
				nurseryList.clear()
				if (snapshot.exists()) {
					for (dataSnapshot in snapshot.children) {
						val studentListModel = dataSnapshot.getValue(StudentListModel::class.java)
						nurseryList.add(studentListModel!!)
						Log.d(TAG, "onDataChange: $nurseryList")
					}
				}
			}
			
			@SuppressLint("LongLogTag")
			override fun onCancelled(error: DatabaseError) {
				Log.d(TAG, "onCancelled: ${error.message}")
			}
		})
		
		
		classNameAdapter.onItemClick(object : RecyclerViewItemClick {
			override fun onItemClick(position: Int, view: View) {
				when (position) {
					0 -> {
						startActivity(
							Intent(this@StudentInformationActivity, StudentListActivity::class.java)
						)
					}
					1 -> {
						startActivity(
							Intent(
								this@StudentInformationActivity,
								NurseryListActivity::class.java
							)
						)
					}
					
				}
			}
		})
		rec_id_student_information.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@StudentInformationActivity)
			adapter = classNameAdapter
		}
		
	}
	
	private fun getClass(): ArrayList<ClassNameModel> {
//		val firebaseDatabase = FirebaseDatabase.getInstance()
//		val reference = firebaseDatabase.reference.child("Class Name")
//		reference.addValueEventListener(object : ValueEventListener {
//			@SuppressLint("NotifyDataSetChanged")
//			override fun onDataChange(snapshot: DataSnapshot) {
//				arrayList.clear()
//				if (snapshot.exists()) {
//					for (dataSnapshot in snapshot.children) {
//						val classNameModel = dataSnapshot.getValue(ClassNameModel::class.java)
//						arrayList.add(classNameModel!!)
//					}
//				}
//				studentInformationAdapter.notifyDataSetChanged()
//			}
//
//			override fun onCancelled(error: DatabaseError) {
//
//			}
//		})
		arrayList = ArrayList()
		arrayList.add(ClassNameModel(className = "Child"))
		arrayList.add(ClassNameModel(className = "Nursery"))
		arrayList.add(ClassNameModel(className = "Class One"))
		arrayList.add(ClassNameModel(className = "Class Two"))
		arrayList.add(ClassNameModel(className = "Class Three"))
		arrayList.add(ClassNameModel(className = "Class Four"))
		arrayList.add(ClassNameModel(className = "Class Five"))
		
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

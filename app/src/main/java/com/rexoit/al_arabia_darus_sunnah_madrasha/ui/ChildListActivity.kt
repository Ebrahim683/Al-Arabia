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
import com.rexoit.al_arabia_darus_sunnah_madrasha.adapter.StudentListAdapter
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.StudentListModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.activity_child_list.*

private const val TAG = "childListActivity"

class StudentListActivity : AppCompatActivity() {
	private lateinit var studentListAdapter: StudentListAdapter
	private lateinit var childList: ArrayList<StudentListModel>
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_child_list)
		
		val toolbar = findViewById<Toolbar>(R.id.toolbar_child_list)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		studentListAdapter = StudentListAdapter()
		studentListAdapter.submitList(child())
		
		studentListAdapter.onItemClick(object : RecyclerViewItemClick {
			override fun onItemClick(position: Int, view: View) {
				val intent = Intent(this@StudentListActivity, StudentDetailsActivity::class.java)
				intent.apply {
					putExtra("name", childList[position].studentName)
					putExtra("age", childList[position].studentAge)
					putExtra("roll", childList[position].studentRoll)
					putExtra("image", childList[position].studentImage)
					putExtra("fatherName", childList[position].studentFatherName)
					putExtra("motherName", childList[position].studentMotherName)
					putExtra("currentAddress", childList[position].studentCurrentAddress)
					putExtra("homeDistrict", childList[position].studentHomeDistrict)
				}
				startActivity(intent)
			}
		})
		
		rec_child.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@StudentListActivity)
			adapter = studentListAdapter
		}
		
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			android.R.id.home -> {
				super.onBackPressed()
			}
		}
		return super.onOptionsItemSelected(item)
	}
	
	private fun child(): ArrayList<StudentListModel> {
		childList = ArrayList()
		val firebaseDatabase = FirebaseDatabase.getInstance()
		val reference = firebaseDatabase.reference.child("ClassChild")
		reference.addValueEventListener(object : ValueEventListener {
			@SuppressLint("NotifyDataSetChanged")
			override fun onDataChange(snapshot: DataSnapshot) {
				childList.clear()
				if (snapshot.exists()) {
					for (dataSnapshot in snapshot.children) {
						val studentListModel = dataSnapshot.getValue(StudentListModel::class.java)
						childList.add(studentListModel!!)
					}
				}
				studentListAdapter.notifyDataSetChanged()
			}
			
			@SuppressLint("LongLogTag")
			override fun onCancelled(error: DatabaseError) {
				Log.d(TAG, "onCancelled: ${error.message}")
			}
		})
		return childList
	}
	
}
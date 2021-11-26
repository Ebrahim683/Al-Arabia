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
import kotlinx.android.synthetic.main.activity_nursery_list.*

private const val TAG = "nurseryListActivity"

class NurseryListActivity : AppCompatActivity() {
	private lateinit var studentListAdapter: StudentListAdapter
	private lateinit var nurseryList: ArrayList<StudentListModel>
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_nursery_list)
		val toolbar = findViewById<Toolbar>(R.id.toolbar_nursery_list)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		studentListAdapter = StudentListAdapter()
		studentListAdapter.submitList(nursery())
		
		studentListAdapter.onItemClick(object : RecyclerViewItemClick {
			override fun onItemClick(position: Int, view: View) {
				val intent = Intent(this@NurseryListActivity, StudentDetailsActivity::class.java)
				intent.apply {
					putExtra("name", nurseryList[position].studentName)
					putExtra("age", nurseryList[position].studentAge)
					putExtra("roll", nurseryList[position].studentRoll)
					putExtra("image", nurseryList[position].studentImage)
					putExtra("fatherName", nurseryList[position].studentFatherName)
					putExtra("motherName", nurseryList[position].studentMotherName)
					putExtra("currentAddress", nurseryList[position].studentCurrentAddress)
					putExtra("homeDistrict", nurseryList[position].studentHomeDistrict)
				}
				startActivity(intent)
			}
		})
		
		rec_nursery.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@NurseryListActivity)
			adapter = studentListAdapter
		}
		
		
	}
	
	private fun nursery(): ArrayList<StudentListModel> {
		nurseryList = ArrayList()
		val firebaseDatabase = FirebaseDatabase.getInstance()
		val reference = firebaseDatabase.reference.child("Nursery")
		reference.addValueEventListener(object : ValueEventListener {
			@SuppressLint("NotifyDataSetChanged")
			override fun onDataChange(snapshot: DataSnapshot) {
				nurseryList.clear()
				if (snapshot.exists()) {
					for (dataSnapshot in snapshot.children) {
						val studentListModel = dataSnapshot.getValue(StudentListModel::class.java)
						nurseryList.add(studentListModel!!)
					}
				}
				studentListAdapter.notifyDataSetChanged()
			}
			
			@SuppressLint("LongLogTag")
			override fun onCancelled(error: DatabaseError) {
				Log.d(TAG, "onCancelled: ${error.message}")
			}
		})
		return nurseryList
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
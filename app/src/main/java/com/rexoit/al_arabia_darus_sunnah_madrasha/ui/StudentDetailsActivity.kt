package com.rexoit.al_arabia_darus_sunnah_madrasha.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import kotlinx.android.synthetic.main.activity_student_details.*

private const val TAG = "studentDetailsActivity"

class StudentDetailsActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_student_details)
		
		val toolbar = findViewById<Toolbar>(R.id.toolbar_student_details)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		
		val studentName = intent.getStringExtra("name")
		val studentAge = intent.getStringExtra("age")
		val studentRoll = intent.getStringExtra("roll")
		val studentImage = intent.getStringExtra("image")
		val studentFatherName = intent.getStringExtra("fatherName")
		val studentMotherName = intent.getStringExtra("motherName")
		val studentCurrentAddress = intent.getStringExtra("currentAddress")
		val studentHomeDistrict = intent.getStringExtra("homeDistrict")
		
		Glide.with(this).load(studentImage).placeholder(R.drawable.avatar).into(st_image)
		name.text = studentName
		age.text = studentAge
		roll.text = studentRoll
		father_name.text = studentFatherName
		mother_name.text = studentMotherName
		c_address.text = studentCurrentAddress
		h_district.text = studentHomeDistrict
		
		
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
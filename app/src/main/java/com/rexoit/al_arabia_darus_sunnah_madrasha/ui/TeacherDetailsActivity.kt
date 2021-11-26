package com.rexoit.al_arabia_darus_sunnah_madrasha.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_teacher_details.*

private const val TAG = "teacherDetailsActivity"

class TeacherDetailsActivity : AppCompatActivity() {
	private lateinit var teacherImage: String
	private lateinit var name: String
	private lateinit var age: String
	private lateinit var mobile: String
	private lateinit var post: String
	private lateinit var education: String
	private lateinit var industry: String
	private lateinit var joinDate: String
	private lateinit var birthDate: String
	private lateinit var birthCertificateNo: String
	private lateinit var nId: String
	private lateinit var bloodGroup: String
	private lateinit var fbId: String
	private lateinit var emailId: String
	private lateinit var fatherName: String
	private lateinit var fatherMobile: String
	private lateinit var fatherNid: String
	private lateinit var fatherBirthCertificateNo: String
	private lateinit var motherName: String
	private lateinit var motherMobile: String
	private lateinit var motherNid: String
	private lateinit var motherBirthCertificateNo: String
	private lateinit var permanentAddress: String
	private lateinit var currentAddress: String
	override fun onCreate(savedInstanceState: Bundle?) {
		
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_teacher_details)
		
		val toolbar = findViewById<Toolbar>(R.id.toolbar_td)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		try {
			teacherImage = intent.getStringExtra("image")!!
			name = intent.getStringExtra("name")!!
			age = intent.getStringExtra("age")!!
			mobile = intent.getStringExtra("mobile")!!
			post = intent.getStringExtra("post")!!
			education = intent.getStringExtra("education")!!
			industry = intent.getStringExtra("industry")!!
			joinDate = intent.getStringExtra("joinDate")!!
			birthDate = intent.getStringExtra("birthDate")!!
			birthCertificateNo = intent.getStringExtra("birthCertificateNo")!!
			nId = intent.getStringExtra("nId")!!
			bloodGroup = intent.getStringExtra("bloodGroup")!!
			fbId = intent.getStringExtra("fbId")!!
			emailId = intent.getStringExtra("emailId")!!
			fatherName = intent.getStringExtra("fatherName")!!
			fatherMobile = intent.getStringExtra("fatherMobile")!!
			fatherNid = intent.getStringExtra("fatherNid")!!
			fatherBirthCertificateNo = intent.getStringExtra("fatherBirthCertificateNo")!!
			motherName = intent.getStringExtra("motherName")!!
			motherMobile = intent.getStringExtra("motherMobile")!!
			motherNid = intent.getStringExtra("motherNid")!!
			motherBirthCertificateNo = intent.getStringExtra("motherBirthCertificateNo")!!
			permanentAddress = intent.getStringExtra("permanentAddress")!!
			currentAddress = intent.getStringExtra("currentAddress")!!
			
			Picasso.get().load(teacherImage).placeholder(R.drawable.avatar).into(teacher_image)
			Picasso.get().load(teacherImage).placeholder(R.drawable.avatar).into(teacher_image_big)
			teacher_name_text.text = "$name"
			teacher_name_text_big.text = "$name"
			teacher_join_text.text = "➣ চাকরি যোগদানঃ $joinDate খৃস্টাব্দ।"
			teacher_post_text.text = "➣ পদবীঃ $post"
			teacher_post_text_big.text = "➣ পদবীঃ $post"
			teacher_age_text.text = "➣ বয়স : $age বছর।"
			teacher_age_text_big.text = "➣ বয়স : $age বছর।"
			teacher_birth_date_text.text = "➣ জন্ম তারিখঃ $birthDate খৃস্টাব্দ।"
			teacher_birth_certificate_text.text = "জন্ম সনদ নংঃ $birthCertificateNo"
			teacher_nid_text.text = "➣ ভোটার আইডি নংঃ $nId"
			teacher_blood_group_text.text = "➣ রক্তের গ্রুপঃ $bloodGroup"
			teacher_mobile_text.text = "➣ মোবাইল নাম্বার : $mobile"
			teacher_fb_text.text = "➣ এফবি আইডিঃ $fbId"
			teacher_email_text.text = "➣ ই-মেইল আইডিঃ $emailId"
			teacher_father_name_text.text = "➣ পিতাঃ $fatherName"
			teacher_father_mobile_text.text = "➣ পিতার মোবাইল নাম্বারঃ $fatherMobile"
			teacher_father_nid_text.text = "➣ পিতার ভোটার আইডি নংঃ $fatherNid"
			teacher_father_birth_certificate_text.text =
				"➣ পিতার জন্ম সনদ নংঃ $fatherBirthCertificateNo"
			teacher_mother_name_text.text = "➣ মাতাঃ $motherName"
			teacher_mother_mobile_text.text = "➣ মাতার মোবাইল নাম্বারঃ $motherMobile"
			teacher_mother_nid_text.text = "➣ মাতার ভোটার আইডি নংঃ $motherNid"
			teacher_mother_birth_certificate_text.text =
				"➣ মাতার জন্ম সনদ নংঃ $motherBirthCertificateNo"
			teacher_permanent_address_text.text = "➣ স্থায়ী ঠিকানাঃ $permanentAddress"
			teacher_current_address_text.text = "➣ বর্তমান ঠিকানাঃ $currentAddress"
			teacher_permanent_address_text.text = "➣ স্থায়ী ঠিকানাঃ $permanentAddress"
			teacher_education_text.text = "➣ Education : $education"
			teacher_industry_text.text = "➣ Industry : $industry"
			
		} catch (e: Exception) {
			Log.d(TAG, "onCreate: $e")
			Toast.makeText(this, "Not enough information", Toast.LENGTH_SHORT).show()
		}
		
		
		btn_show_teacher_details.setOnClickListener {
			teacher_transformation_layout.startTransform()
		}
		
		btn_down_teacher_details.setOnClickListener {
			teacher_transformation_layout.finishTransform()
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
	
}
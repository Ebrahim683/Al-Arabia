package com.rexoit.al_arabia_darus_sunnah_madrasha.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.storage.FirebaseStorage
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.adapter.MainActivityAdapter
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.MainRecModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "mainActivity"

class MainActivity : AppCompatActivity() {
	private lateinit var uri: Uri
	
	@SuppressLint("RestrictedApi")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		
		banner_image.setOnClickListener {
			val intent = Intent(Intent.ACTION_GET_CONTENT)
			intent.apply {
				type = "image/*"
			}
			startActivityForResult(intent,121)
		}
		
		name_text.setOnClickListener {
			upload()
		}
		
		
		val list = ArrayList<MainRecModel>()
		list.add(MainRecModel(R.drawable.ic_android_black_24dp, "প্রাতিষ্ঠানিক তথ্য"))
		list.add(MainRecModel(R.drawable.ic_android_black_24dp, "ছাত্র তথ্য"))
		list.add(MainRecModel(R.drawable.ic_android_black_24dp, "নোটিশ"))
		list.add(MainRecModel(R.drawable.ic_android_black_24dp, "সিলেবাস ও পাঠ্য পরিকল্পনা"))
		list.add(MainRecModel(R.drawable.ic_android_black_24dp, "আমল"))
		list.add(MainRecModel(R.drawable.ic_android_black_24dp, "লাইভ"))
		
		val mainActivityAdapter = MainActivityAdapter()
		mainActivityAdapter.onRecyclerItemClick(object : RecyclerViewItemClick {
			override fun onItemClick(position: Int, view: View) {
				when (position) {
					0 -> {
						startActivity(
							Intent(
								this@MainActivity,
								MadrashaInformationActivity::class.java
							)
						)
					}
					1 -> {
						startActivity(
							Intent(
								this@MainActivity,
								StudentInformationActivity::class.java
							)
						)
					}
					2 -> {
						startActivity(
							Intent(
								this@MainActivity,
								MadrashaInformationActivity::class.java
							)
						)
					}
					3 -> {
						startActivity(
							Intent(
								this@MainActivity,
								MadrashaInformationActivity::class.java
							)
						)
					}
					4 -> {
						startActivity(
							Intent(
								this@MainActivity,
								MadrashaInformationActivity::class.java
							)
						)
					}
					5 -> {
						startActivity(
							Intent(
								this@MainActivity,
								MadrashaInformationActivity::class.java
							)
						)
					}
					
				}
			}
		})
		mainActivityAdapter.submitList(list)
		rec_id.apply {
			setHasFixedSize(true)
			layoutManager = GridLayoutManager(this@MainActivity, 2)
			adapter = mainActivityAdapter
		}
	}
	
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == 121 && resultCode == RESULT_OK && data?.data != null) {
			uri = data?.data!!
			banner_image.setImageURI(uri)
		}
	}
	
	private fun upload() {
		val firebaseStorage = FirebaseStorage.getInstance()
		val storageReference = firebaseStorage.reference.child("Image")
		
		storageReference.putFile(uri).addOnCompleteListener {
			if (it.isSuccessful) {
				Log.d(TAG, "upload: done")
			} else {
				Log.d(TAG, "upload: ${it.exception}")
			}
		}
	}
	
}
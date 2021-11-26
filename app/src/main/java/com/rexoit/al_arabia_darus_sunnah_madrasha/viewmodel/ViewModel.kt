package com.rexoit.al_arabia_darus_sunnah_madrasha.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.TeacherInformationModel

private const val TAG = "viewModel"

class ViewModel : ViewModel() {
	
	val madrashaText = MutableLiveData<String>()
	fun getMadrashaInformation() {
		val firebaseDatabase = FirebaseDatabase.getInstance()
		val reference = firebaseDatabase.getReference("MadrashaPorichiti")
		val madrashaPorichiti = object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				madrashaText.value = snapshot.getValue<String>()
			}
			
			override fun onCancelled(error: DatabaseError) {
				madrashaText.value = error.message
				Log.d(TAG, "getMadrashaInformation: ${error.message.toString()}")
			}
		}
		reference.addValueEventListener(madrashaPorichiti)
	}
	
	val teacherInformation = MutableLiveData<TeacherInformationModel>()
	fun getTeacherInformation() {
		val firebaseDatabase = FirebaseDatabase.getInstance()
		val reference = firebaseDatabase.reference.child("TeacherInformation")
		val tInformation = object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				teacherInformation.value = snapshot.getValue(TeacherInformationModel::class.java)
			}
			
			override fun onCancelled(error: DatabaseError) {
				Log.d(TAG, "getTeacherInformation: ${error.message.toString()}")
			}
		}
		reference.addValueEventListener(tInformation)
	}
	
}
package com.rexoit.al_arabia_darus_sunnah_madrasha.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.adapter.MadrashaInformationAdapter
import com.rexoit.al_arabia_darus_sunnah_madrasha.model.MainRecModel
import com.rexoit.al_arabia_darus_sunnah_madrasha.utils.RecyclerViewItemClick
import kotlinx.android.synthetic.main.activity_madrasha_information.*

class MadrashaInformationActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_madrasha_information)
		
		val toolbar = findViewById<Toolbar>(R.id.toolbar_inf)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		val list = ArrayList<MainRecModel>()
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Teacher Information"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		list.add(
			MainRecModel(
				image = R.drawable.ic_android_black_24dp,
				name = "Madrasha Porichiti"
			)
		)
		
		
		val madrashaInformationAdapter = MadrashaInformationAdapter()
		madrashaInformationAdapter.submitList(list)
		madrashaInformationAdapter.onRecyclerItemClick(object : RecyclerViewItemClick {
			override fun onItemClick(position: Int, view: View) {
				when (position) {
					0 -> {
						startActivity(
							Intent(
								this@MadrashaInformationActivity,
								MadrashaPorichitiActivity::class.java
							)
						)
					}
					1 -> {
						startActivity(
							Intent(
								this@MadrashaInformationActivity,
								TeacherInformationActivity::class.java
							)
						)
					}
				}
			}
		})
		
		rec_id_information.apply {
			setHasFixedSize(true)
			layoutManager = LinearLayoutManager(this@MadrashaInformationActivity)
			adapter = madrashaInformationAdapter
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
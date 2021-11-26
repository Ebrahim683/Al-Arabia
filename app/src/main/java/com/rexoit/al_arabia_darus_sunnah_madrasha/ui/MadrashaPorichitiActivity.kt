package com.rexoit.al_arabia_darus_sunnah_madrasha.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rexoit.al_arabia_darus_sunnah_madrasha.R
import com.rexoit.al_arabia_darus_sunnah_madrasha.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_madrasha_porichiti.*

class MadrashaPorichitiActivity : AppCompatActivity() {
	private lateinit var viewModel: ViewModel
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_madrasha_porichiti)
		
		val toolbar = findViewById<Toolbar>(R.id.toolbar_madrasha_Porichiti)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		viewModel = ViewModelProvider(this)[ViewModel::class.java]
		viewModel.getMadrashaInformation()
		viewModel.madrashaText.observe(this, Observer {
			madrasha_porichiti_text.text = it.toString()
		})
		
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
package com.rexoit.al_arabia_darus_sunnah_madrasha.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rexoit.al_arabia_darus_sunnah_madrasha.R

private const val TAG = "splashScreenActivity"

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash_screen)
		this.window.statusBarColor = resources.getColor(R.color.white)
		
		Handler().postDelayed({
			try {
				startActivity(Intent(this, MainActivity::class.java))
				finish()
			} catch (e: Exception) {
				Log.d(TAG, "SplashScreenActivity: ${e.message}")
			}
		}, 1500)
		
	}
}
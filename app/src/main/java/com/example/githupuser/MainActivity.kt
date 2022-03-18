package com.example.githupuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.githupuser.intent.ListUserActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intentToListUserActivity = Intent(this@MainActivity,ListUserActivity::class.java)
                startActivity(intentToListUserActivity)
                finish()
            },3000
        )

    }
}
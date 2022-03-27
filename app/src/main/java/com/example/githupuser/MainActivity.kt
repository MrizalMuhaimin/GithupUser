package com.example.githupuser

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githupuser.data.SettingPreferences
import com.example.githupuser.databinding.FragmentSettingBinding
import com.example.githupuser.intent.ListUserActivity
import com.example.githupuser.ui.factory.ThemeViewModelFactory
import com.example.githupuser.viewmodel.ThemeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var  viewBinding: FragmentSettingBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        viewBinding = FragmentSettingBinding.inflate(layoutInflater,)

        val pref = SettingPreferences.getInstance(dataStore)


        val viewModel = ViewModelProvider(this, ThemeViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )

        viewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    viewBinding.settingSwitchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    viewBinding.settingSwitchTheme.isChecked = false
                }
            }
        )

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intentToListUserActivity = Intent(this@MainActivity,ListUserActivity::class.java)
                startActivity(intentToListUserActivity)
                finish()
            },3000
        )

    }
}
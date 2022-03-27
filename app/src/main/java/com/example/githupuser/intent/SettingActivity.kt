package com.example.githupuser.intent

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.githupuser.R
import com.example.githupuser.ui.fragment.SettingFragment

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.hide()

        val mFragmenManager = supportFragmentManager
        val mFragmentSetting = SettingFragment()

        val fragment = mFragmenManager.findFragmentByTag(SettingFragment::class.java.simpleName)
        if(fragment !is SettingFragment){
            mFragmenManager
                .beginTransaction()
                .add(R.id.fragment_setting_container,mFragmentSetting, SettingFragment::class.java.simpleName)
                .commit()
        }
    }
}
package com.example.githupuser.intent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

import com.example.githupuser.R
import com.example.githupuser.databinding.ActivityListUserBinding
import com.example.githupuser.ui.fragment.ListUserFragment
import com.example.githupuser.viewmodel.ListViewModel

class ListUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ListViewModel::class.java)

        mainViewModel.isLoading.observe( this,{
            showLoading(it)
        })

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}

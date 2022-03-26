package com.example.githupuser.intent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.githupuser.R
import com.example.githupuser.databinding.ActivityListUserBinding
import com.example.githupuser.viewmodel.ListViewModel
import com.example.githupuser.viewmodel.TitleActivityModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ListUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_list)


        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_favorite
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        binding.homeEtSearch.setOnEditorActionListener{ _, actionId, _ ->
            var handled = false

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchUser()
                handled = true
            }
            handled
        }

        binding.homeBtnSearch.setOnClickListener {
            searchUser()
        }

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ListViewModel::class.java)
        val titelViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(TitleActivityModel::class.java)
        titelViewModel.title.observe(this,{
            binding.homeToolbarTittle.text = it
        })

        mainViewModel.isLoading.observe( this,{
            showLoading(it)
        })

        mainViewModel.dataSearch.observe(this,{
            if(it.total_count==0){
                binding.clNotFound.visibility = View.VISIBLE
            }else{
                binding.clNotFound.visibility = View.GONE
            }
        })

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun searchUser(){
        if (!binding.homeEtSearch.text.isBlank()) {
            val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ListViewModel::class.java)
            mainViewModel.searchUser(binding.homeEtSearch.text.toString())
            mainViewModel.isLoading.observe( this,{
                showLoading(it)
            })
        }

    }
}

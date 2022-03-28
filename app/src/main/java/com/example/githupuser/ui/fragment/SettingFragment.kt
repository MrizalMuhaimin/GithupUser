package com.example.githupuser.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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



class SettingFragment() : Fragment() {

    private var _viewBinding: FragmentSettingBinding ? =null
    private val viewBinding get() = _viewBinding!!
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _viewBinding = FragmentSettingBinding.inflate(inflater,container,false)

        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = view.context

        val pref = SettingPreferences.getInstance(requireActivity().dataStore)

//        val viewModel: ThemeViewModel by lazy {
//            ViewModelProvider(this).get(ThemeViewModel::class.java)
//        }

        val viewModel = ViewModelProvider(this, ThemeViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )

        viewModel.getThemeSettings().observe(viewLifecycleOwner,
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

        viewBinding.settingBtnBack.setOnClickListener {
            activity?.finish()
        }

        viewBinding.settingSwitchTheme.setOnCheckedChangeListener{_, isChaked ->
            viewModel.saveThemeSetting(isChaked)
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}
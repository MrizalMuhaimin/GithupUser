package com.example.githupuser.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githupuser.R
import com.example.githupuser.ui.fragment.DetailUserFragment
import com.example.githupuser.ui.model.UserModel

class DetailUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val detailUser = intent.getStringExtra(TAG_LOGIN_USER)
        val mFragmenManager = supportFragmentManager
        val mdetailUserFragment =  DetailUserFragment()
        val mbundle = Bundle()

        title = detailUser.toString()

        if (mbundle != null ) {
            mbundle.putString(TAG_LOGIN_USER,detailUser)
            mdetailUserFragment.arguments = mbundle
        }

        val fragment = mFragmenManager.findFragmentByTag(DetailUserFragment::class.java.simpleName)
        if(fragment !is DetailUserFragment){
            mFragmenManager
                .beginTransaction()
                .add(R.id.fragment_detail_container,mdetailUserFragment,DetailUserFragment::class.java.simpleName)
                .commit()
        }
    }

    companion object {
        const val TAG_LOGIN_USER ="TAG_LOGIN_USER"
    }
}
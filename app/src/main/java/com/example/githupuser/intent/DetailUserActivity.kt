package com.example.githupuser.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githupuser.R
import com.example.githupuser.ui.fragment.DetailUserFragment
import com.example.githupuser.ui.model.UserModel

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val TAG_DETAIL_USER ="TAG_DETAIL_USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val detailUser = intent.getParcelableExtra<UserModel>(TAG_DETAIL_USER) as UserModel
        val mFragmenManager = supportFragmentManager
        val mdetailUserFragment =  DetailUserFragment()
        val mbundle = Bundle()

        setTitle(detailUser.username)
        if (mbundle != null ) {
            mbundle.putParcelable(TAG_DETAIL_USER,detailUser)
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
}
package com.example.githupuser.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githupuser.R
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.adapter.ListUserAdapter
import com.example.githupuser.ui.model.UserModel
import com.example.githupuser.ui.util.GithupUserList


class ListUserFragment : Fragment() {

    private lateinit var rvListUser: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_list_user, container, false)
        rvListUser = view.findViewById(R.id.rv_list_user)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let{
            rvListUser.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)

            val adapter = ListUserAdapter(GithupUserList.LIST_USER)
            rvListUser.adapter = adapter

            adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
                override fun onItemClicked(user: UserModel) {
                    val intentToDetail = Intent(requireActivity(),DetailUserActivity::class.java)
                    intentToDetail.putExtra(DetailUserActivity.TAG_DETAIL_USER, user)
                    startActivity(intentToDetail)
                }
            })
        }
    }




}
package com.example.githupuser.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githupuser.databinding.FragmentFollowerBinding
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.adapter.ListUserAdapter
import com.example.githupuser.viewmodel.FollowViewModel

class FollowerFragment : Fragment() {
    private lateinit var ViewBinding: FragmentFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ViewBinding = FragmentFollowerBinding.inflate(inflater, container, false)
        return ViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            FollowViewModel::class.java)

        context?.let{
            ViewBinding.rvListUser.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)

            mainViewModel.dataFollower.observe( viewLifecycleOwner,{
                val adapter = ListUserAdapter(it)
                ViewBinding.rvListUser.adapter = adapter

                adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
                    override fun onItemClicked(user: String?) {
                        activity?.finish()
                        val intentToDetail = Intent(requireActivity(), DetailUserActivity::class.java)
                        intentToDetail.putExtra(DetailUserActivity.TAG_LOGIN_USER, user)
                        startActivity(intentToDetail)
                    }
                })
            })
        }
    }
}
package com.example.githupuser.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githupuser.data.model.UserSearch
import com.example.githupuser.databinding.FragmentListUserBinding
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.adapter.ListUserAdapter
import com.example.githupuser.viewmodel.ListViewModel


class ListUserFragment : Fragment() {

    private lateinit var mViewBinding: FragmentListUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewBinding = FragmentListUserBinding.inflate(inflater, container, false)
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(ListViewModel::class.java)

        context?.let{
            mViewBinding.rvListUser.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)

            mainViewModel.dataSearch.observe( viewLifecycleOwner,{

                val adapter = ListUserAdapter(it.items)
                mViewBinding.rvListUser.adapter = adapter

                adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
                    override fun onItemClicked(user: String?) {
                        val intentToDetail = Intent(requireActivity(),DetailUserActivity::class.java)
                        intentToDetail.putExtra(DetailUserActivity.TAG_LOGIN_USER, user)
                        startActivity(intentToDetail)
                    }
                })
            })
        }
    }
}
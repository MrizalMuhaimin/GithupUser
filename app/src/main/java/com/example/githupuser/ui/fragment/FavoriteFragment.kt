package com.example.githupuser.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githupuser.data.model.UserSearch
import com.example.githupuser.databinding.ActivityListUserBinding
import com.example.githupuser.databinding.FragmentFavoriteBinding
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.adapter.ListUserAdapter
import com.example.githupuser.ui.factory.FavoriteModelFactory
import com.example.githupuser.ui.main.FavoriteViewModel
import com.example.githupuser.viewmodel.TitleActivityModel


class FavoriteFragment : Fragment() {

    private lateinit var mFavoriteViewModel: FavoriteViewModel
    private lateinit var mViewBinding: FragmentFavoriteBinding
    private lateinit var mActivityBinding: ActivityListUserBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return mViewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            TitleActivityModel::class.java)

        mFavoriteViewModel = obtainFavoriteViewModel(requireActivity() as AppCompatActivity)


        titleViewModel.updateActionBarTitle("Favorite")


        context?.let{
            mViewBinding.rvListUser.layoutManager = LinearLayoutManager(it, RecyclerView.VERTICAL, false)

            mFavoriteViewModel.getAllFavorite().observe( viewLifecycleOwner) {
                var listUserSearch: MutableList<UserSearch> = mutableListOf()

                it.forEach { unit ->
                    val userSearch =
                        UserSearch(
                            unit.login,
                            unit.id,
                            unit.node_id,
                            unit.avatar_url,
                            unit.gravatar_id,
                            unit.url,
                            unit.html_url,
                            unit.followers_url,
                            unit.following_url,
                            unit.gists_url,
                            unit.starred_url,
                            unit.subscriptions_url,
                            unit.repos_url,
                            unit.repos_url,
                            unit.events_url,
                            unit.received_events_url,
                            unit.type,
                            unit.site_admin,
                            0
                        )

                    if (userSearch != null) {
                        listUserSearch.add(userSearch)
                    }


                }

                val adapter = ListUserAdapter(listUserSearch)
                mViewBinding.rvListUser.adapter = adapter

                adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
                    override fun onItemClicked(user: String?) {
                        val intentToDetail =
                            Intent(requireActivity(), DetailUserActivity::class.java)
                        intentToDetail.putExtra(DetailUserActivity.TAG_LOGIN_USER, user)
                        startActivity(intentToDetail)
                    }
                })
            }
        }

    }
    private fun obtainFavoriteViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }


}
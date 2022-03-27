package com.example.githupuser.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githupuser.R
import com.example.githupuser.data.db.entities.UserEntity
import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.databinding.FragmentDetailUserBinding
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.factory.FavoriteModelFactory
import com.example.githupuser.ui.insert.FavoriteAddUpdateViewModel
import com.example.githupuser.ui.main.FavoriteViewModel
import com.example.githupuser.ui.pageradapter.SectionsPagerAdapter
import com.example.githupuser.viewmodel.DetailViewModel
import com.example.githupuser.viewmodel.FollowViewModel
import com.google.android.material.tabs.TabLayoutMediator


class DetailUserFragment : Fragment() {

    private lateinit var viewBinding: FragmentDetailUserBinding
    private lateinit var mainViewModel : DetailViewModel
    private var userEntity: UserEntity? = null
    private lateinit var mFavoriteAddUpdateViewModel : FavoriteAddUpdateViewModel
    private lateinit var mFavoriteViewModel:  FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentDetailUserBinding.inflate(inflater,container,false)

        return  viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = view.context

        val dataDetailUser = arguments?.getString(DetailUserActivity.TAG_LOGIN_USER)


        mFavoriteAddUpdateViewModel = obtainFavoriteAddUpdateViewModel(requireActivity() as AppCompatActivity)
        mFavoriteViewModel = obtainFavoriteViewModel(requireActivity() as AppCompatActivity)

        mFavoriteViewModel.getUserByLogin(dataDetailUser.toString()).observe(viewLifecycleOwner, {
            if(it.size !=0){
                userEntity = it.get(0)
                renderOffline(view)

            }else{
                userEntity = null
            }
            checkIsFavorite(view)

        })

        mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        val folloViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)

        viewBinding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(viewBinding.tabs, viewBinding.viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
            if(position==0){
                folloViewModel.setDataFollower(dataDetailUser.toString())

            }else if(position==1){
                folloViewModel.setDataFollowing(dataDetailUser.toString())
            }

            folloViewModel.isLoading.observe(viewLifecycleOwner,{
                showLoading(it)
            })

        }.attach()

        if(userEntity==null){
            if(arguments != null){
                mainViewModel.setDetailUser(dataDetailUser.toString())
                mainViewModel.isLoading.observe(viewLifecycleOwner, {
                    showLoading(it)
                })

                mainViewModel.dataUsers.observe(viewLifecycleOwner,{
                    renderAllDetai(it, view)
                })

            }else{
                viewBinding.tvNameGithupDetail.text ="null"
            }

        }

        viewBinding.ivFavorite.setOnClickListener{
            if (userEntity != null) {
                // Delete from favorite user
                deleteFavorite()
            } else {
                // Add to favorite user
                addFavorite()
            }

        }
    }

    fun renderAllDetai (it: UserDetail, view: View){
        viewBinding.tvNameGithupDetail.text = it.name?: "-"
        viewBinding.tvCompanyGithupDetail.text = it.company?: "-"
        viewBinding.tvLokasiGithupDetail.text = it.location?: "-"
        viewBinding.tvValRepositoryGithupDetail.text = it.public_repos.toString()?: "-"
        viewBinding.tvValFollowerGithupDetail.text = it.followers.toString()?: "-"
        viewBinding.tvValFollowingGithupDetail.text = it.following.toString()?: "-"

        viewBinding.ivShare.setOnClickListener{ itt ->
            val message = "["+ it.login+"]\n"+ it.name + " work on " +it.company +
                    "\nRepositories: "+it.public_repos.toString()+
                    "\nFollower: "+it.followers.toString()+
                    "\nFollowing: " +it.following.toString()+
                    "\n" + it.html_url

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type = "text/plain"

            try {
                startActivity(intent)
            } catch (t: ActivityNotFoundException) {
                t.printStackTrace()
            }
        }

        val context = view.context
        Glide.with(context)
            .load(it.avatar_url)
            .into(viewBinding.ivUserImageDetail)
    }

    private fun renderOffline(view: View){
        val userDetai = UserDetail(
            userEntity!!.login,
            userEntity!!.id,
            userEntity!!.node_id,
            userEntity!!.avatar_url,
            userEntity!!.gravatar_id,
            userEntity!!.url,
            userEntity!!.html_url,
            userEntity!!.followers_url,
            userEntity!!.following_url,
            userEntity!!.gists_url,
            userEntity!!.starred_url,
            userEntity!!.subscriptions_url,
            userEntity!!.organization_url,
            userEntity!!.repos_url,
            userEntity!!.events_url,
            userEntity!!.received_events_url,
            userEntity!!.type,
            userEntity!!.site_admin,
            userEntity!!.name,
            userEntity!!.company,
            userEntity!!.blog,
            userEntity!!.location,
            userEntity!!.email,
            userEntity!!.hireable,
            userEntity!!.bio,
            userEntity!!.twitter_username,
            userEntity!!.public_repos,
            userEntity!!.public_gists,
            userEntity!!.followers,
            userEntity!!.following,
            userEntity!!.created_at,
            userEntity!!.updated_at,
        )
        renderAllDetai(userDetai,view)
        viewBinding.tvNameGithupDetail.text = "userEntity!!.name"

    }

    private fun obtainFavoriteAddUpdateViewModel(activity: AppCompatActivity): FavoriteAddUpdateViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteAddUpdateViewModel::class.java)
    }

    private fun obtainFavoriteViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }

    private fun showLoading(isLoading: Boolean) {
        viewBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun checkIsFavorite(view: View) {
        val context = view.context

        if(userEntity != null){
            val imgInt = resources.getIdentifier("@drawable/ic_baseline_favorite_24","drawable",context.packageName )
            viewBinding.ivFavorite.setImageResource(imgInt)
            viewBinding.ivFavorite.setColorFilter(Color.parseColor("#FF0000"))
        }else{
            val imgInt = resources.getIdentifier("@drawable/ic_baseline_favorite_border_24","drawable",context.packageName )
            viewBinding.ivFavorite.setImageResource(imgInt)
            viewBinding.ivFavorite.setColorFilter(Color.parseColor("#4c566a"))

        }
    }


    private fun addFavorite(){
        mainViewModel.dataUsers.observe(viewLifecycleOwner) {
            val user = UserEntity(
                it.id,
                it.login,
                it.node_id,
                it.avatar_url,
                it.gravatar_id,
                it.url,
                it.html_url,
                it.followers_url,
                it.following_url,
                it.gists_url,
                it.starred_url,
                it.subscriptions_url,
                it.organization_url,
                it.repos_url,
                it.events_url,
                it.received_events_url,
                it.type,
                it.site_admin,
                it.name,
                it.company,
                it.blog,
                it.location,
                it.email,
                it.hireable,
                it.bio,
                it.twitter_username,
                it.public_repos,
                it.public_gists,
                it.followers,
                it.following,
                it.created_at,
                it.updated_at,
            )
            userEntity = user
            mFavoriteAddUpdateViewModel.insert(userEntity!!)
        }
    }

    private fun deleteFavorite(){
        mFavoriteAddUpdateViewModel.delete(userEntity!!)
        userEntity = null


    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
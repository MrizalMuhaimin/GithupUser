package com.example.githupuser.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githupuser.R
import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.databinding.FragmentDetailUserBinding
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.model.UserModel
import com.example.githupuser.viewmodel.DetailViewModel
import com.example.githupuser.viewmodel.ListViewModel
import de.hdodenhof.circleimageview.CircleImageView


class DetailUserFragment : Fragment() {

    private lateinit var viewBinding: FragmentDetailUserBinding

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

        if(arguments != null){

            val dataDetailUser = arguments?.getString(DetailUserActivity.TAG_LOGIN_USER)
            val mainViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
                DetailViewModel::class.java)

            mainViewModel.setDetailUser(dataDetailUser.toString())

            mainViewModel.dataUsers.observe(viewLifecycleOwner,{
                renderAllDetai(it, view)
            })

        }else{
            viewBinding.tvNameGithupDetail.text ="null"
        }
    }
    fun renderAllDetai (it: UserDetail, view: View){
        val context = view.context
        Glide.with(context)
            .load(it.avatar_url)
            .into(viewBinding.ivUserImageDetail)

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
                    "\n" + it.repos_url

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

    }

}
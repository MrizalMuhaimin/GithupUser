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
import com.example.githupuser.R
import com.example.githupuser.databinding.FragmentDetailUserBinding
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.model.UserModel
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

            val dataDetailUser = arguments?.getParcelable<UserModel>(DetailUserActivity.TAG_DETAIL_USER)
            val imgInt = resources.getIdentifier(dataDetailUser?.avatar,"drawable",context.packageName )
            viewBinding.ivUserImageDetail.setImageResource(imgInt)
            viewBinding.tvNameGithupDetail.text = dataDetailUser?.name
            viewBinding.tvCompanyGithupDetail.text = dataDetailUser?.company
            viewBinding.tvLokasiGithupDetail.text = dataDetailUser?.location
            viewBinding.tvValRepositoryGithupDetail.text = dataDetailUser?.repository.toString()
            viewBinding.tvValFollowerGithupDetail.text = dataDetailUser?.follower.toString()
            viewBinding.tvValFollowingGithupDetail.text = dataDetailUser?.following.toString()

            viewBinding.ivShare.setOnClickListener{
                val message = "["+ dataDetailUser?.username+"]\n"+ dataDetailUser?.name + " work on " +dataDetailUser?.company +
                        "\nRepositories: "+dataDetailUser?.repository.toString()+
                        "\nFollower: "+dataDetailUser?.follower.toString()+
                        "\nFollowing: " +dataDetailUser?.following.toString()
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

        }else{
            viewBinding.tvNameGithupDetail.text ="null"
        }


    }


}
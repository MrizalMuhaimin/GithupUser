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
import com.example.githupuser.intent.DetailUserActivity
import com.example.githupuser.ui.model.UserModel
import de.hdodenhof.circleimageview.CircleImageView


class DetailUserFragment : Fragment() {
    private lateinit var tv_name_detail : TextView
    private lateinit var tv_company_detail : TextView
    private lateinit var tv_lokasi_detail : TextView
    private lateinit var tv_repository_detail : TextView
    private lateinit var tv_follower_detail : TextView
    private lateinit var tv_following_detail : TextView
    private lateinit var iv_share : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail__user_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_name_detail = view.findViewById(R.id.tv_name_githup_detail)
        tv_company_detail = view.findViewById(R.id.tv_company_githup_detail)
        tv_lokasi_detail = view.findViewById(R.id.tv_lokasi_githup_detail)
        tv_repository_detail = view.findViewById(R.id.tv_val_repository_githup_detail)
        tv_follower_detail = view.findViewById(R.id.tv_val_follower_githup_detail)
        tv_following_detail = view.findViewById(R.id.tv_val_following_githup_detail)
        iv_share = view.findViewById(R.id.iv_share)
        val iv_profil: CircleImageView = view.findViewById(R.id.iv_user_image_detail)
        val context = view.context

        if(arguments != null){

            val dataDetailUser = arguments?.getParcelable<UserModel>(DetailUserActivity.TAG_DETAIL_USER)
            val imgInt = resources.getIdentifier(dataDetailUser?.avatar,"drawable",context.packageName )
            iv_profil.setImageResource(imgInt)
            tv_name_detail.text = dataDetailUser?.name
            tv_company_detail.text = dataDetailUser?.company
            tv_lokasi_detail.text = dataDetailUser?.location
            tv_repository_detail.text = dataDetailUser?.repository.toString()
            tv_follower_detail.text = dataDetailUser?.follower.toString()
            tv_following_detail.text = dataDetailUser?.following.toString()

            iv_share.setOnClickListener{
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
            tv_name_detail.text ="null"
        }


    }


}
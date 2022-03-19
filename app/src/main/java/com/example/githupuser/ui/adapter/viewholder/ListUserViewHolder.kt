package com.example.githupuser.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githupuser.R
import com.example.githupuser.data.model.UserSearch
import de.hdodenhof.circleimageview.CircleImageView

class ListUserViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val tvUserName: TextView = view.findViewById(R.id.tv_username_githup)
    private val tvName: TextView = view.findViewById(R.id.tv_name_githup)
    private val iv_profil: CircleImageView = view.findViewById(R.id.iv_user_image)
    val context = view.context


    fun onBind(element:UserSearch){
        tvUserName.text = element.login
        tvName.text = element.type
        Glide.with(context)
            .load(element.avatar_url)
            .into(iv_profil)


    }

}
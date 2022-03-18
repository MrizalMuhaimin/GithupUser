package com.example.githupuser.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githupuser.R
import com.example.githupuser.ui.model.UserModel
import de.hdodenhof.circleimageview.CircleImageView

class ListUserViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val tvUserName: TextView = view.findViewById(R.id.tv_username_githup)
    private val tvName: TextView = view.findViewById(R.id.tv_name_githup)
    private val iv_profil: CircleImageView = view.findViewById(R.id.iv_user_image)
    val context = view.context

    fun onBind(element:UserModel){
        val imgInt = context.resources.getIdentifier(element.avatar,"drawable",context.packageName )
        tvUserName.text = element.username
        tvName.text = element.name
        Glide.with(context)
            .load(imgInt)
            .into(iv_profil)


    }

}
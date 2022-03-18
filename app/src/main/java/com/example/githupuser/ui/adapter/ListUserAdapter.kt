package com.example.githupuser.ui.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githupuser.R
import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.data.model.UserSearch
import com.example.githupuser.data.model.response.SearchResponse
import com.example.githupuser.ui.adapter.viewholder.ListUserViewHolder
import com.example.githupuser.ui.model.UserModel

class ListUserAdapter(private val listUser: MutableList<UserSearch>?):
    RecyclerView.Adapter<ListUserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListUserViewHolder(
            inflater.inflate(R.layout.fragment_item_list_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.onBind(listUser!![position])
        holder.itemView.setOnClickListener{
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
        }
    }

    override fun getItemCount(): Int = listUser!!.size

    interface OnItemClickCallback {
        fun onItemClicked(user: UserSearch)
    }


}
package com.indialone.mvvmandrxjava.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indialone.mvvmandrxjava.databinding.ItemLayoutBinding
import com.indialone.mvvmandrxjava.roomdatabase.User
import com.indialone.mvvmandrxjava.utils.Constants

class UserRecyclerAdapter(
    private var activity: Activity
) : RecyclerView.Adapter<UserRecyclerAdapter.UserRecyclerViewHolder>() {

    private val users = ArrayList<User>()

    class UserRecyclerViewHolder(itemView: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val tvUsername = itemView.tvUsername
        private val tvEmail = itemView.tvEmail
        private val tvFavourite = itemView.tvFavourite
        private val tvHobby = itemView.tvHobby
        private val tvCountry = itemView.tvCountry
        val ivDelete = itemView.ivDelete
        val ivEdit = itemView.ivEdit

        fun bind(user: User) {
            tvUsername.text = user.username
            tvEmail.text = user.email
            tvHobby.text = user.hobby
            tvFavourite.text = user.favourite
            tvCountry.text = user.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerViewHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRecyclerViewHolder, position: Int) {
        holder.bind(users[position])

        holder.ivDelete.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity).deleteUser(users[position])
            }
        }

        holder.ivEdit.setOnClickListener {
            val intent = Intent(activity , AddUserActivity::class.java)
            intent.putExtra(Constants.USER_DATA , users[position])
            activity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addAll(list: List<User>) {
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }

}
package com.definex.pagingsampleapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.definex.pagingsampleapp.R
import com.definex.pagingsampleapp.databinding.ItemUserBinding
import javax.inject.Inject

class UsersAdapter @Inject constructor():
PagingDataAdapter<UserItemUiState, UserViewHolder>(Comparator){

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState ->
            holder.bind(userItemUiState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    object Comparator: DiffUtil.ItemCallback<UserItemUiState>(){
        override fun areContentsTheSame(
            oldItem: UserItemUiState,
            newItem: UserItemUiState
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: UserItemUiState, newItem: UserItemUiState): Boolean {
            return oldItem.getPhone() == newItem.getPhone()
        }
    }
}
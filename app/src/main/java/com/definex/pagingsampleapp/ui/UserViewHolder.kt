package com.definex.pagingsampleapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.definex.pagingsampleapp.databinding.ItemUserBinding
import com.definex.pagingsampleapp.extension.executeWithAction

class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(userItemUiState: UserItemUiState) {
        binding.executeWithAction {
            this.userItemUiState = userItemUiState
        }
    }
}
package com.definex.pagingsampleapp.ui

import com.definex.pagingsampleapp.data.UserModel

data class UserItemUiState(private val userModel: UserModel): BaseUiState() {
    fun getImageUrl() = userModel.pictureModel.thumbnail

    fun getName() = "${userModel.name.name} ${userModel.name.surname}"

    fun getPhone() = userModel.phone

    fun getMail() = userModel.email
}
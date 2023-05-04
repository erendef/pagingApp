package com.definex.pagingsampleapp.repository

import androidx.paging.PagingData
import com.definex.pagingsampleapp.data.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<PagingData<UserModel>>
}
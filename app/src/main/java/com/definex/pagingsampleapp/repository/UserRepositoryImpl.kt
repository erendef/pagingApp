package com.definex.pagingsampleapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.definex.pagingsampleapp.UserPagingDataSource
import com.definex.pagingsampleapp.data.UserModel
import com.definex.pagingsampleapp.data.UserService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
): UserRepository {
    override fun getUsers(): Flow<PagingData<UserModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                UserPagingDataSource(userService)
            }
        ).flow
    }
}
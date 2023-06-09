package com.definex.pagingsampleapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.definex.pagingsampleapp.data.UserModel
import com.definex.pagingsampleapp.data.UserService

class UserPagingDataSource(private val userService: UserService): PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        val page = params.key ?: 1
        return try {
            val response = userService.getUsers(page,params.loadSize)
            LoadResult.Page(
                data = response.results,
                prevKey = if(page == 1) null else page.minus(1),
                nextKey = if(response.results.isEmpty()) null else page.plus(1)
            )
        } catch (exception: java.lang.Exception) {
            return LoadResult.Error(exception)
        }

    }

}
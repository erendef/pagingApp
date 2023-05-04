package com.definex.pagingsampleapp.ui

import androidx.paging.LoadState

class UsersUiState(
    private val loadState: LoadState
): BaseUiState() {

    fun getProgressBarVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)

    fun getListVisibility() = getViewVisibility(isVisible = loadState is LoadState.NotLoading)

    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)

    fun getErrorMessage() = if (loadState is LoadState.Error) {
        loadState.error.localizedMessage ?:     "Something went wrong!"
    } else ""

}
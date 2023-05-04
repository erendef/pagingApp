package com.definex.pagingsampleapp.ui

import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.definex.pagingsampleapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel() {
    var userItemsUiStates = userRepository.getUsers()
        .map {pagingData->
            pagingData.map {userModel ->
                UserItemUiState(userModel)
            }
        }
}
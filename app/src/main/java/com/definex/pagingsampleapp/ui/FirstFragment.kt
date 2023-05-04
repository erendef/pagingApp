package com.definex.pagingsampleapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.definex.pagingsampleapp.R
import com.definex.pagingsampleapp.databinding.FragmentFirstBinding
import com.definex.pagingsampleapp.extension.collect
import com.definex.pagingsampleapp.extension.collectLast
import com.definex.pagingsampleapp.extension.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val pagingViewModel: PagingViewModel by viewModels()

    @Inject
    lateinit var usersAdapter: UsersAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.rvUsers.adapter = usersAdapter
        collect(flow = usersAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
        action = ::setUsersUiState)
        collectLast(pagingViewModel.userItemsUiStates, ::setUsers)
        return binding.root
    }

    private fun setUsersUiState(loadState: LoadState) {
        binding.executeWithAction {
            usersUiState = UsersUiState(loadState)
        }
    }

    private suspend fun setUsers(userItemsPagingData: PagingData<UserItemUiState>) {
        usersAdapter.submitData(userItemsPagingData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
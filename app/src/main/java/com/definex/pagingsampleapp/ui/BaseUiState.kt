package com.definex.pagingsampleapp.ui

import android.view.View

open class BaseUiState {
    fun getViewVisibility(isVisible: Boolean) = if(isVisible) View.VISIBLE else View.GONE
}
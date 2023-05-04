package com.definex.pagingsampleapp.extension

import androidx.databinding.ViewDataBinding

fun <T: ViewDataBinding> T.executeWithAction(action: T.() -> Unit) {
    action()
    executePendingBindings()
}
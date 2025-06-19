package com.vompom.blog.ui.component

/**
 *
 * Created by @juliswang on 2025/06/17 21:46
 *
 * @Description
 */

sealed class PageState {
    object Loading
    object Error
    object Success
}
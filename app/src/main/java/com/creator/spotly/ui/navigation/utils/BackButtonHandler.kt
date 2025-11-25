package com.creator.spotly.ui.navigation.utils

import androidx.navigation3.runtime.NavBackStack


fun backButtonHandler(stack: NavBackStack) = stack.removeLastOrNull()

package com.edsonlimadev.shopapp.utils


abstract class StateUi<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : StateUi<T>(data = null, message = null)

    class Error<T>(message: String?) : StateUi<T>(message = message)

    class Success<T>(data: T, message: String? = null) : StateUi<T>(data = data, message = message)
}
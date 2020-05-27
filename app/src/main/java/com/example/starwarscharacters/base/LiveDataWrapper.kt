package com.example.starwarscharacters.base

class LiveDataWrapper<T>(
    val responseStatus: ResponseStatus,
    val response: MutableList<T>? = null,
    val error: Throwable? = null
) {

    enum class ResponseStatus {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> loading() = LiveDataWrapper<T>(ResponseStatus.LOADING)
        fun <T> success(data: MutableList<T>) = LiveDataWrapper(ResponseStatus.SUCCESS, data)
        fun <T> error(err: Throwable) = LiveDataWrapper<T>(ResponseStatus.ERROR, null, err)
    }
}
package com.assignment.interviewdemo.retrofitHelper

open class RequestResult<out T> private constructor(
    val status: Status,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): RequestResult<T> {
            return RequestResult(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> success(msg: String? = null, data: T): RequestResult<T> {
            return RequestResult(
                Status.SUCCESS,
                data,
                msg
            )
        }

        fun <T> error(msg: String? = null, data: T? = null): RequestResult<T> {
            return RequestResult(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T? = null): RequestResult<T> {
            return RequestResult(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
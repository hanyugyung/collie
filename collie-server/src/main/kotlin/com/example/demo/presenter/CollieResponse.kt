package com.example.demo.presenter

import com.example.demo.CollieCodes

class CollieResponse<T>(
    val code: Int,
    val data: T?,
    val message: String? = null,
){
    companion object{
        fun <T> success(data:T, message:String? = null):CollieResponse<T>{
            return CollieResponse(code = CollieCodes.SUCCESS.code, data = data, message = message)
        }
    }
}

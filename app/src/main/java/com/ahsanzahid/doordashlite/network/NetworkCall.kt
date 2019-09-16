package com.ahsanzahid.doordashlite.network

import androidx.lifecycle.MutableLiveData
import com.ahsanzahid.doordashlite.model.Outcome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class NetworkCall<T> {
    lateinit var call: Call<T>

    fun makeCall(call: Call<T>): MutableLiveData<Outcome<T>> {
        this.call = call
        val callBackKt = CallBackKt<T>()
        callBackKt.result.value = Outcome.loading(true)
        this.call.enqueue(callBackKt)
        return callBackKt.result
    }

    class CallBackKt<T> : Callback<T> {
        var result: MutableLiveData<Outcome<T>> = MutableLiveData()

        override fun onFailure(call: Call<T>, t: Throwable) {
            result.value = Outcome.failure(t)
            t.printStackTrace()
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful)
                response.body()?.let { body ->
                    result.value = Outcome.success(body)
                }
            else {
                result.value = Outcome.failure(
                    Throwable(
                        response.errorBody()?.string() ?: "Network Error occurred"
                    )
                )
            }
        }
    }

    fun cancel() {
        if (::call.isInitialized) {
            call.cancel()
        }
    }
}
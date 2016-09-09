package ru.maxlord.kotlinandroidapp.rest.model.common

import com.google.gson.annotations.SerializedName

/**
 *
 *
 * @author Lord (Kuleshov M.V.)
 * @since 21.03.16
 */
class Status {
    companion object {
        const val CODE_SUCCESS = 0
        const val CODE_SESSION_TIMEOUT = 1
        const val CODE_ERROR = 2
    }

    /**
     * code : 0
     * error :
     */

    @SerializedName("code")
    var code: Int = 0
    @SerializedName("error")
    var error: String = ""


    fun isSuccess(): Boolean {
        return code == CODE_SUCCESS
    }

    fun isSessionTimeout(): Boolean {
        return code == CODE_SESSION_TIMEOUT
    }

    fun isError(): Boolean {
        return code == CODE_ERROR
    }

    override fun toString(): String {
        return "Status(code=$code, error='$error')"
    }
}

package com.said.homework.base.data.exception

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.said.homework.base.data.model.ApiErrorJson
import com.said.homework.base.domain.exception.AppException
import retrofit2.Response
import java.io.IOException

class RetrofitException private constructor(
    override var message: String?,
    private val responseBody: String?,
    val responseCode: Int,
    /**
     * The event kind which triggered this error.
     */
    val kind: Kind,
    val exception: Throwable?,
    val errorCode: Int
) : AppException(exception) {

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.
     *
     * @throws IOException         if unable to convert the body to the specified `type`.
     * @throws JsonSyntaxException in case of bad GatWay the response is html not json so it's invalid JSON
     */
    @Throws(IOException::class, JsonSyntaxException::class)
    fun <T> getErrorBodyAs(type: Class<T>?): T? {
        return if (responseBody == null || responseBody.isEmpty()) {
            null
        } else sGson.fromJson(responseBody, type)
    }

    /**
     * Identifies the event kind which triggered a [RetrofitException].
     */
    enum class Kind {
        /**
         * An [IOException] occurred while communicating to the server.
         */
        NETWORK,

        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,

        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    companion object {
        private val sGson = Gson()
        fun httpError(response: Response<*>?): RetrofitException {
            var message: String? = null
            var responseBody = ""
            var responseCode = 0
            var errorCode = 0
            if (response != null) {
                responseCode = response.code()
            }
            try {
                if (response != null && response.errorBody() != null) {
                    responseBody = response.errorBody()!!.string()
                }
                val apiErrorJson = sGson.fromJson(
                    responseBody,
                    ApiErrorJson::class.java
                )
                if (apiErrorJson != null) {
                    message = apiErrorJson.message
                    errorCode = apiErrorJson.code
                }
            } catch (e: Exception) {
                message = responseCode.toString() + " " + response!!.message()
                e.printStackTrace()
            }
            return RetrofitException(
                message,
                responseBody,
                responseCode,
                Kind.HTTP,
                null,
                errorCode
            )
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(exception.message, null, 0, Kind.NETWORK, exception, 0)
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(exception.message, null, 0, Kind.UNEXPECTED, exception, 0)
        }
    }
}
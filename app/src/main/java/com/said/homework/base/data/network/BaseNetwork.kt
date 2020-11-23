package com.said.homework.base.data.network

import android.util.Log.INFO
import com.said.homework.AppConstants
import com.said.homework.BuildConfig
import com.said.homework.base.data.network.AppGSONConverterFactory.factory
import com.said.homework.base.data.util.LanguageUtil.Companion.locale
import com.said.homework.base.presentation.util.CrashUtil
import com.said.homework.base.presentation.util.CrashUtil.logFireBaseCrash
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseNetwork @Inject constructor() {
    //        mAccessTokenValue = userTokenSharedPrefs.getAccessToken();
    private var accessToken: String? = null
        set
    private var mOkHttpClient: OkHttpClient? = null
    private val mAuthenticator: Authenticator? = null
    private val mHttpLoggingInterceptor: HttpLoggingInterceptor
    private fun applyRequestHeaders(
        requestBuilder: Request.Builder,
        requestType: RequestType
    ): Request {
        when (requestType) {
            RequestType.API_REQUEST -> {
                var accessToken = accessToken
                if (!accessToken!!.startsWith("Bearer ")) {
                    accessToken = "Bearer $accessToken"
                }
                requestBuilder.header(OAUTH_AUTH_HEADER_KEY, accessToken)
                requestBuilder.header(ACCEPT_HEADER_KEY, "application/json")
                requestBuilder.header(LANG_HEADER_KEY, locale.language)
            }
            RequestType.NO_AUTH_HEADERS_REQUEST -> requestBuilder.header(
                LANG_HEADER_KEY,
                locale.language
            )
            RequestType.DEFAULT_NO_HEADERS_REQUEST -> {
            }
        }
        return requestBuilder.build()
    }

    private fun retrofit(
        requestClient: OkHttpClient,
        useRxAdapter: Boolean,
        getStringResponse: Boolean
    ): Retrofit {
        val mRetrofitBuilder = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(if (getStringResponse) ScalarsConverterFactory.create() else factory)
            .client(requestClient)
        return mRetrofitBuilder.build()
    }

    private fun getOkHttpClientBuilder(
        requestType: RequestType,
        connectTimeout: Long,
        showLog: Boolean
    ): OkHttpClient.Builder {
        val okHttpClientBuilder: OkHttpClient.Builder
        if (mOkHttpClient == null) {
            mOkHttpClient = OkHttpClient()
            okHttpClientBuilder = mOkHttpClient!!.newBuilder()
            okHttpClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            okHttpClientBuilder.readTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            okHttpClientBuilder.writeTimeout(connectTimeout, TimeUnit.MILLISECONDS)
        } else {
            okHttpClientBuilder = mOkHttpClient!!.newBuilder()
            okHttpClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            okHttpClientBuilder.readTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            okHttpClientBuilder.writeTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            okHttpClientBuilder.interceptors().clear()
        }
        okHttpClientBuilder.addInterceptor(
            Interceptor { chain: Interceptor.Chain ->
                val requestBuilder = chain.request().newBuilder()
                val request = applyRequestHeaders(requestBuilder, requestType)
                chain.proceed(request)
            }
        )
        if (BuildConfig.DEBUG && showLog) {
            okHttpClientBuilder.addInterceptor(mHttpLoggingInterceptor)
        }
        return okHttpClientBuilder
    }

    fun <T> create(
        clazz: Class<T>?,
        requestType: RequestType
    ): T {
        return create(clazz, requestType, 0, true, false)
    }

    fun <T> create(
        clazz: Class<T>?,
        requestType: RequestType,
        getStringResponse: Boolean
    ): T {
        return create(clazz, requestType, 0, true, getStringResponse)
    }

    fun <T> create(
        clazz: Class<T>?,
        requestType: RequestType,
        connectionTimeout: Long,
        showLog: Boolean
    ): T {
        return create(clazz, requestType, connectionTimeout, showLog, false)
    }

    fun <T> create(
        clazz: Class<T>?,
        requestType: RequestType,
        connectionTimeout: Long,
        showLog: Boolean,
        getStringResponse: Boolean
    ): T {
        var connectionTimeout = connectionTimeout
        if (connectionTimeout == 0L) {
            connectionTimeout = DEFAULT_REQUEST_TIMEOUT.toLong()
        }
        val okHttpClient = getClient(requestType, connectionTimeout, showLog)
        return retrofit(okHttpClient, true, getStringResponse).create(clazz)
    }

    /**
     * @param reqType type
     * @param connectTimeout timeout
     * @param showLog        used to disable log in uploading/download files to prevent logging file info
     * @return ok http client
     */
    private fun getClient(
        reqType: RequestType,
        connectTimeout: Long,
        showLog: Boolean
    ): OkHttpClient {
        initAuthenticator()
        return getOkHttpClientBuilder(
            reqType,
            connectTimeout,
            showLog
        ) //                .authenticator(mAuthenticator)
            .build()
    }

    /***
     * authenticate called when api request throw 401 UnAuthorized Access
     * then we need to request another access token using refresh token.
     * if the refreshToken is Expired we will post event to ui to force user to logout
     * @return Authenticator
     */
    @Synchronized
    private fun initAuthenticator() {
//        if (mAuthenticator == null) {
//            mAuthenticator = (route, response) ->
//                    refreshToken(response);
//        }
    }

    companion object {
        private const val DEFAULT_REQUEST_TIMEOUT = 120000
        const val OAUTH_AUTH_HEADER_KEY = "Authorization"
        const val ACCEPT_HEADER_KEY = "Accept"
        private const val LANG_HEADER_KEY = "lang"
    }

    init {
        mHttpLoggingInterceptor = HttpLoggingInterceptor { message: String? ->
            logFireBaseCrash(CrashUtil.BASE_NETWORK_EXCEPTION, message)
            message?.let { Platform.get().log(it, INFO, null) }
        }
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
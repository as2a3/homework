package com.said.homework.base.data.network

/**
 * Enum of available request types.
 *  * [.DEFAULT_NO_HEADERS_REQUEST]
 *  * [.NO_AUTH_HEADERS_REQUEST]
 *  * [.API_REQUEST]
 */
enum class RequestType {
    /**
     * **Description** Client with no headers at all.
     * For server requests check [.API_REQUEST] <br></br>
     * For requests with no auth headers check  [.NO_AUTH_HEADERS_REQUEST]<br></br>
     */
    DEFAULT_NO_HEADERS_REQUEST,

    /**
     * **Description**  Client with headers that don't include the authentication headers.
     * For server requests check [.API_REQUEST]<br></br>
     * For requests with no headers check [.DEFAULT_NO_HEADERS_REQUEST]<br></br>
     */
    NO_AUTH_HEADERS_REQUEST,

    /**
     * **Description** Client with custom headers used in all API calls that need authentication.
     * For Auth requests check [.NO_AUTH_HEADERS_REQUEST]<br></br>
     * For requests with no headers check [.DEFAULT_NO_HEADERS_REQUEST]
     */
    API_REQUEST
}
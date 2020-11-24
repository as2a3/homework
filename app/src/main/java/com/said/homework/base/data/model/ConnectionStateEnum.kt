package com.said.homework.base.data.model

enum class ConnectionStateEnum(val status: String) {
    OK("ok"), ERROR("error");

    companion object {
        fun mapStringToConnectionState(text: String?): ConnectionStateEnum? {
            for (connectionStateEnum in values()) {
                if (connectionStateEnum.status.equals(text, ignoreCase = true)) {
                    return connectionStateEnum
                }
            }
            return null
        }
    }
}
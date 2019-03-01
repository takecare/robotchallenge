package uk.co.ruibot.robots.robot

sealed class Result<T> {
    val contentOrNull: T?
        get() = (this as? Payload)?.content ?: (this as? Error)?.content
}

data class Payload<T>(val content: T) : Result<T>()
data class Error<T>(val message: String? = null, val content: T? = null) : Result<T>()

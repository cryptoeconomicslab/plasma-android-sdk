package com.cryptoeconomicslab.plasma_android_sdk.httpClient.error

class ApplicationError {
    /**
     * InternalError Exception of Client
     */
    class InternalError(message:String): Exception(message)

    /**
     * InvalidParameters Exception of Client
     */
    class InvalidParameter(message:String): Exception(message)

    /**
     * NotFound Exception of Client
     */
    class NotFound(message:String): Exception(message)
}
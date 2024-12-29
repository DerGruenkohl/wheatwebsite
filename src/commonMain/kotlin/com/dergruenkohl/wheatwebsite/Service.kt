package com.dergruenkohl.wheatwebsite

import io.kvision.annotations.KVService

@KVService
interface IPingService {
    suspend fun ping(message: String): String
}

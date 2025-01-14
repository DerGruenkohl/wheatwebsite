package com.dergruenkohl.wheatwebsite

import com.dergruenkohl.wheatwebsite.service.IPingService
import io.kvision.remote.getService

object Model {

    private val pingService = getService<IPingService>()

    suspend fun ping(message: String): String {
        return pingService.ping(message)
    }

}

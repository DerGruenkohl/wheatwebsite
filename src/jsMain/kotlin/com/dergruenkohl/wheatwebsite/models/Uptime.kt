package com.dergruenkohl.wheatwebsite.models

import com.dergruenkohl.wheatwebsite.service.IUptimeService
import com.dergruenkohl.wheatwebsite.service.Member
import io.kvision.remote.getService

object Uptime {
    private val uptimeService = getService<IUptimeService>()

    suspend fun getUptime(ign: String): Member {
        return uptimeService.uptime(ign)
    }
}
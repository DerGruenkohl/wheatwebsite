package com.dergruenkohl.wheatwebsite.service
import com.dergruenkohl.wheatwebsite.getMinecraftUUID
import com.dergruenkohl.wheatwebsite.hypixel.ApiHandler

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class UptimeService : IUptimeService {
    override suspend fun uptime(ign: String): Member {
        val uuid = getMinecraftUUID(ign)
        val uptime = ApiHandler.getPlayerExpHistory(ign)!!
        return Member(uuid,ign, uptime)
    }
}
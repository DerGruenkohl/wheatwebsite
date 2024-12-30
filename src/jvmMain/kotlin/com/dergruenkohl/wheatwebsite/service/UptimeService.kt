package com.dergruenkohl.wheatwebsite.service
import com.dergruenkohl.wheatwebsite.getMinecraftUUID
import com.dergruenkohl.wheatwebsite.hypixel.ApiHandler

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class UptimeService : IUptimeService {
    override suspend fun uptime(ign: String): Member {
        val uuid = getMinecraftUUID(ign)
        val id = ApiHandler.getGuildIDPlayer(uuid)
        val guild = ApiHandler.getExpHistoryFromApi(id)!!
        return guild.members.find { it.uuid.replace("-","") == uuid.replace("-","") }!!
    }
}
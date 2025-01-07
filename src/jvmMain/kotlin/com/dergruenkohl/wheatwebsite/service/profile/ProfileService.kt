package com.dergruenkohl.wheatwebsite.service.profile

import com.dergruenkohl.wheatwebsite.getMinecraftUUID
import com.dergruenkohl.wheatwebsite.hypixel.EliteApi

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class ProfileService : IProfileService {
    override suspend fun getProfile(ign: String): ProfileReply {
        val uuid = getMinecraftUUID(ign)
        val sbProfile = EliteApi.getSkyblockProfileStats(uuid)
        return ProfileReply(
            uuid = uuid,
            ign = ign,
            uptime = null,
            historicalUptime = null,
            sb = sbProfile,
            gain = listOf(),
        )
    }
}
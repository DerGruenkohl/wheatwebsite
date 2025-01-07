package com.dergruenkohl.wheatwebsite.hypixel

import com.dergruenkohl.wheatwebsite.Api
import com.dergruenkohl.wheatwebsite.getMinecraftUUID
import com.dergruenkohl.wheatwebsite.hypixel.EliteApi.getSkyblockProfileStats
import com.dergruenkohl.wheatwebsite.service.profile.IngoingProfile
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.util.logging.*

object EliteApi {
    private val LOGGER = KtorSimpleLogger("EliteApi")
    suspend fun getSkyblockProfileStats(uuid: String): IngoingProfile {
        LOGGER.info("Getting Skyblock profile stats for $uuid")
        val profile = Api.client.get("https://api.elitebot.dev/profile/$uuid/selected").body<IngoingProfile>()
        println(profile)
        return profile
    }

}
suspend fun main() {
    getSkyblockProfileStats(getMinecraftUUID("Biermaschine"))
}
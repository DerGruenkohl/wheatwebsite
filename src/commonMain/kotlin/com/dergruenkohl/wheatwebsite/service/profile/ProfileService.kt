package com.dergruenkohl.wheatwebsite.service.profile

import com.dergruenkohl.wheatwebsite.service.GraphPlayer
import com.dergruenkohl.wheatwebsite.service.Member
import com.dergruenkohl.wheatwebsite.service.Skills
import io.kvision.annotations.KVService
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@KVService
interface IProfileService {
    suspend fun getProfile(ign: String): ProfileReply
}

@Serializable
data class ProfileReply(
    val ign: String,
    val uuid: String,
    val uptime: Member? = null,
    val historicalUptime: Member? = null,
    val sb: IngoingProfile,
    val gain: List<GraphPlayer>
)
@Serializable
data class SkyblockReply(
    val sblvl: Int,
    val rank: String,
    val skills: Skills? = null,
    val discord: String
)


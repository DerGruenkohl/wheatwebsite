package com.dergruenkohl.wheatwebsite.database.outgoing

import com.dergruenkohl.wheatwebsite.hypixel.ApiHandler
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val discordId: Long,
    val uuid: String,
    val discordName: String? = null,
    val settings: Settings = Settings(profileID = ApiHandler.getSelectedProfile(uuid))
)
@Serializable
data class Settings(
    val track: Boolean = false,
    val pestGain: Boolean = false,
    val collectionGain: Boolean = false,
    val uptime: Boolean = true,
    val customImage: Boolean = false,
    val textColor: String? = null,
    val profileID: String
)
package com.dergruenkohl.wheatwebsite.service.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable




@Serializable
data class IngoingProfile(
    val api: Api,
    val skyblockXp: Int,
    val collections: Collections?,
    val skills: Skills,

)
@Serializable
data class Api(
    val inventories: Boolean,
    val collections: Boolean,
    val skills: Boolean,
    val vault: Boolean
)
@Serializable
data class Skills(
    val combat: Double,
    val mining: Double,
    val foraging: Double,
    val fishing: Double,
    val enchanting: Double,
    val alchemy: Double,
    val taming: Double,
    val carpentry: Double,
    val runecrafting: Double,
    val social: Double,
    val farming: Double,
)

@Serializable
data class Collections(
    @SerialName("CARROT_ITEM")
    val carrot: Long,
    @SerialName("CACTUS")
    val cactus: Long,
    @SerialName("SUGAR_CANE")
    val sugarCane: Long,
    @SerialName("PUMPKIN")
    val pumpkin: Long,
    @SerialName("WHEAT")
    val wheat: Long,
    @SerialName("SEEDS")
    val seeds: Long,
    @SerialName("MUSHROOM_COLLECTION")
    val mushroom: Long,
    @SerialName("NETHER_STALK")
    val wart: Long,
    @SerialName("MELON")
    val melon: Long,
    @SerialName("POTATO_ITEM")
    val potato: Long,
    @SerialName("INK_SACK:3")
    val cocoaBeans: Long
)



@Serializable
data class IngoingPlayer(
    val monthlyPackageRank: String? = null,
    val newPackageRank: String? = null,
    val prefix: String? = null,
    val rank: String? = null,
    val rankPlusColor: String? = null,
    val socialMedia: SocialMedia = SocialMedia(),
    val uuid: String
)
@Serializable
data class SocialMedia(
    val discord: String? = null,
    val hypixel: String? = null,
    val youtube: String? = null
)
package com.dergruenkohl.wheatwebsite.database.outgoing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("playerUuid")
    val uuid: String,
    val timestamp: Long = System.currentTimeMillis(),
    val collections: Collections,
    @SerialName("farmingWeight")
    val weight: Weight
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

//WEIGHT SHIT
@Serializable
data class Weight(
    @SerialName("cropWeight")
    val cropWeight: CropWeight,
    @SerialName("totalWeight")
    val totalWeight: Double,
    @SerialName("uncountedCrops")
    val uncountedCrops: UncountedCrops
)

@Serializable
data class CropWeight(
    @SerialName("Cactus")
    val cactus: Double,
    @SerialName("Carrot")
    val carrot: Double,
    @SerialName("Cocoa Beans")
    val cocoaBeans: Double,
    @SerialName("Melon")
    val melon: Double,
    @SerialName("Mushroom")
    val mushroom: Double,
    @SerialName("Nether Wart")
    val wart: Double,
    @SerialName("Potato")
    val potato: Double,
    @SerialName("Pumpkin")
    val pumpkin: Double,
    @SerialName("Sugar Cane")
    val sugarCane: Double,
    @SerialName("Wheat")
    val wheat: Double
)
@Serializable
data class UncountedCrops(
    @SerialName("Cactus")
    val cactus: Int,
    @SerialName("Carrot")
    val carrot: Int,
    @SerialName("Cocoa Beans")
    val cocoaBeans: Int,
    @SerialName("Melon")
    val melon: Int,
    @SerialName("Mushroom")
    val mushroom: Int,
    @SerialName("Nether Wart")
    val wart: Int,
    @SerialName("Potato")
    val potato: Int,
    @SerialName("Pumpkin")
    val pumpkin: Int,
    @SerialName("Sugar Cane")
    val sugarCane: Int,
    @SerialName("Wheat")
    val wheat: Int
)
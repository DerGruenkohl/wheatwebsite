package com.dergruenkohl.wheatwebsite.database.dao

import com.dergruenkohl.wheatwebsite.database.outgoing.*
import com.dergruenkohl.wheatwebsite.database.table.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

// Player DAO

class PlayerEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlayerEntity>(PlayerTable)

    var uuid by PlayerTable.uuid
    var timestamp by PlayerTable.timestamp
    var collections by CollectionsEntity referencedOn PlayerTable.collections
    var weight by WeightEntity referencedOn PlayerTable.weight

    fun toPlayer(): Player = Player(
        uuid = uuid,
        timestamp = timestamp,
        collections = collections.toCollections(),
        weight = weight.toWeight()
    )
}


// Collections DAO
class CollectionsEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CollectionsEntity>(CollectionsTable)

    var carrot by CollectionsTable.carrot
    var cactus by CollectionsTable.cactus
    var cane by CollectionsTable.cane
    var pumpkin by CollectionsTable.pumpkin
    var wheat by CollectionsTable.wheat
    var seeds by CollectionsTable.seeds
    var mushroom by CollectionsTable.mushroom
    var wart by CollectionsTable.wart
    var melon by CollectionsTable.melon
    var potato by CollectionsTable.potato
    var cocoa by CollectionsTable.cocoa

    fun toCollections(): Collections = Collections(
        carrot = carrot,
        cactus = cactus,
        sugarCane = cane,
        pumpkin = pumpkin,
        wheat = wheat,
        seeds = seeds,
        mushroom = mushroom,
        wart = wart,
        melon = melon,
        potato = potato,
        cocoaBeans = cocoa
    )
}

// Weight DAO
class WeightEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WeightEntity>(WeightTable)

    var totalWeight by WeightTable.totalWeight
    var cropWeight by CropWeightEntity referencedOn WeightTable.cropWeight
    var uncountedCrops by UncountedCropsEntity referencedOn WeightTable.uncountedCrops

    fun toWeight(): Weight = Weight(
        cropWeight = cropWeight.toCropWeight(),
        totalWeight = totalWeight,
        uncountedCrops = uncountedCrops.toUncountedCrops()
    )
}

// CropWeight DAO
class CropWeightEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CropWeightEntity>(CropWeightTable)

    var cactus by CropWeightTable.cactus
    var carrot by CropWeightTable.carrot
    var cocoaBeans by CropWeightTable.cocoaBeans
    var melon by CropWeightTable.melon
    var mushroom by CropWeightTable.mushroom
    var netherWart by CropWeightTable.netherWart
    var potato by CropWeightTable.potato
    var pumpkin by CropWeightTable.pumpkin
    var sugarCane by CropWeightTable.sugarCane
    var wheat by CropWeightTable.wheat

    fun toCropWeight(): CropWeight = CropWeight(
        cactus = cactus,
        carrot = carrot,
        cocoaBeans = cocoaBeans,
        melon = melon,
        mushroom = mushroom,
        wart = netherWart,
        potato = potato,
        pumpkin = pumpkin,
        sugarCane = sugarCane,
        wheat = wheat
    )
}

// UncountedCrops DAO
class UncountedCropsEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UncountedCropsEntity>(UncountedCropsTable)

    var cactus by UncountedCropsTable.cactus
    var carrot by UncountedCropsTable.carrot
    var cocoaBeans by UncountedCropsTable.cocoaBeans
    var melon by UncountedCropsTable.melon
    var mushroom by UncountedCropsTable.mushroom
    var netherWart by UncountedCropsTable.netherWart
    var potato by UncountedCropsTable.potato
    var pumpkin by UncountedCropsTable.pumpkin
    var sugarCane by UncountedCropsTable.sugarCane
    var wheat by UncountedCropsTable.wheat

    fun toUncountedCrops(): UncountedCrops = UncountedCrops(
        cactus = cactus,
        carrot = carrot,
        cocoaBeans = cocoaBeans,
        melon = melon,
        mushroom = mushroom,
        wart = netherWart,
        potato = potato,
        pumpkin = pumpkin,
        sugarCane = sugarCane,
        wheat = wheat
    )
}

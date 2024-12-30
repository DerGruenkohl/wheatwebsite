package com.dergruenkohl.wheatwebsite.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column


// Player table
object PlayerTable : IntIdTable("players") {
    val uuid: Column<String> = varchar("uuid", 36)
    val timestamp: Column<Long> = long("timestamp")
    val weight = reference("weight_id", WeightTable)
    val collections = reference("collections_id", CollectionsTable)
}

// Collections table
object CollectionsTable : IntIdTable("collections") {
    val carrot = long("carrot")
    val cactus = long("cactus")
    val cane = long("cane")
    val pumpkin = long("pumpkin")
    val wheat = long("wheat")
    val seeds = long("seeds")
    val mushroom = long("mushroom")
    val wart = long("wart")
    val melon = long("melon")
    val potato = long("potato")
    val cocoa = long("cocoa")
}

// Weight table
object WeightTable : IntIdTable("weights") {
    val totalWeight = double("total_weight")
    val cropWeight = reference("crop_weight_id", CropWeightTable)
    val uncountedCrops = reference("uncounted_crops_id", UncountedCropsTable)
}

// CropWeight table
object CropWeightTable : IntIdTable("crop_weights") {
    val cactus = double("cactus")
    val carrot = double("carrot")
    val cocoaBeans = double("cocoa_beans")
    val melon = double("melon")
    val mushroom = double("mushroom")
    val netherWart = double("nether_wart")
    val potato = double("potato")
    val pumpkin = double("pumpkin")
    val sugarCane = double("sugar_cane")
    val wheat = double("wheat")
}

// UncountedCrops table
object UncountedCropsTable : IntIdTable("uncounted_crops") {
    val cactus = integer("cactus")
    val carrot = integer("carrot")
    val cocoaBeans = integer("cocoa_beans")
    val melon = integer("melon")
    val mushroom = integer("mushroom")
    val netherWart = integer("nether_wart")
    val potato = integer("potato")
    val pumpkin = integer("pumpkin")
    val sugarCane = integer("sugar_cane")
    val wheat = integer("wheat")
}

object WeightTable2 : IntIdTable("weight_track") {
    val timestamp = long("timestamp")
    val totalWeight = double("total_weight")
    val cropWeight = reference("crop_weight_id", CropWeightsTable)
    val uncountedCrops = reference("uncounted_crops_id", PestCropsTable)
}

// CropWeight table
object CropWeightsTable : IntIdTable("crop_weight_track") {
    val timestamp = long("timestamp")
    val cactus = double("cactus")
    val carrot = double("carrot")
    val cocoaBeans = double("cocoa_beans")
    val melon = double("melon")
    val mushroom = double("mushroom")
    val netherWart = double("nether_wart")
    val potato = double("potato")
    val pumpkin = double("pumpkin")
    val sugarCane = double("sugar_cane")
    val wheat = double("wheat")
}

object PestCropsTable : IntIdTable("pest_crop_track") {
    val timestamp = long("timestamp")
    val cactus = long("cactus")
    val carrot = long("carrot")
    val cocoaBeans = long("cocoa_beans")
    val melon = long("melon")
    val mushroom = long("mushroom")
    val netherWart = long("nether_wart")
    val potato = long("potato")
    val pumpkin = long("pumpkin")
    val sugarCane = long("sugar_cane")
    val wheat = long("wheat")
}

object SkillTrackTable: IntIdTable("skill_track") {
    val timestamp = long("timestamp")
    val member = reference("member_id", LinkTable)
    val taming = long("taming")
    val mining = long("mining")
    val foraging = long("foraging")
    val enchanting = long("enchanting")
    val carpentry = long("carpentry")
    val social = long("social")
    val farming = long("farming")
    val combat = long("combat")
    val fishing = long("fishing")
    val alchemy = long("alchemy")
    val runecrafting = long("runecrafting")
}

object DungeonTable: IntIdTable("dungeon_track") {
    val timestamp = long("timestamp")
    val level = long("level")
    val archer = long("archer")
    val berserk = long("berserk")
    val healer = long("healer")
    val mage = long("mage")
}

object SlayerTable: IntIdTable("slayer_track") {
    val timestamp = long("timestamp")
    val rev = long("rev")
    val tara = long("tara")
    val sven  = long("sven")
    val eman = long("eman")
    val blaze = long("blaze")
    val vamp = long("vamp")
}

object MiningTable: IntIdTable("mining_track") {
    val timestamp = long("timestamp")
    val lapis = long("lapis")
    val redstone = long("redstone")
    val umber = long("umber")
    val coal  = long("coal")
    val mycelium = long("mycelium")
    val endstone = long("endstone")
    val quartz = long("quartz")
    val sand  = long("sand")
    val iron = long("iron")
    val gemstone  = long("gemstone")
    val tungsten  = long("tungsten")
    val obsidian  = long("obsidian")
    val diamond = long("diamond")
    val cobble = long("cobble")
    val glowstone  = long("glowstone")
    val gold = long("gold")
    val gravel = long("gravel")
    val hardstone = long("hardstone")
    val mithril = long("mithril")
    val emerald = long("emerald")
    val redsand = long("redsand")
    val ice = long("ice")
    val glacite = long("glace")
    val sulphur = long("sulphur")
    val netherrack = long("netherrack")
}

object CombatTable

object ForagingTable

object FishingTable

object RiftTable

object BossCollectionTable

object GenericDataTable





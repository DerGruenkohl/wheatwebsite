package com.dergruenkohl.wheatwebsite.database.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

// Link table definition
object LinkTable : LongIdTable("links") {
    val discordId: Column<Long> = long("discord_id").uniqueIndex()
    val uuid: Column<String> = varchar("uuid", 36).uniqueIndex()
    val discordName: Column<String?> = varchar("discord_name", 255).nullable()
    val settings = reference("settings_id", SettingsTable)
}

// Settings table definition
object SettingsTable : LongIdTable("settings") {
    val track: Column<Boolean> = bool("track").default(false)
    val pestGain: Column<Boolean> = bool("pest_gain").default(false)
    val collectionGain: Column<Boolean> = bool("collection_gain").default(false)
    val uptime: Column<Boolean> = bool("uptime").default(true)
    val customImage: Column<Boolean> = bool("custom_image").default(false)
    val textColor: Column<String?> = varchar("text_color", 50).nullable()
    val profileID: Column<String> = varchar("profile_id", 50)
}
package com.dergruenkohl.wheatwebsite.database.dao


import com.dergruenkohl.wheatwebsite.database.outgoing.*
import com.dergruenkohl.wheatwebsite.database.table.*
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class LinkEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<LinkEntity>(LinkTable)

    var discordId by LinkTable.discordId
    var uuid by LinkTable.uuid
    var discordName by LinkTable.discordName
    var settings by SettingsEntity referencedOn LinkTable.settings

    // Convert DAO to Data class
    fun toLink(): Link = Link(
        discordId = discordId,
        uuid = uuid,
        discordName = discordName,
        settings = settings.toSettings()
    )
}

// Settings DAO
class SettingsEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<SettingsEntity>(SettingsTable)

    var track by SettingsTable.track
    var pestGain by SettingsTable.pestGain
    var collectionGain by SettingsTable.collectionGain
    var uptime by SettingsTable.uptime
    var customImage by SettingsTable.customImage
    var textColor by SettingsTable.textColor
    var profileID by SettingsTable.profileID

    // Convert DAO to Data class
    fun toSettings(): Settings = Settings(
        track = track,
        pestGain = pestGain,
        collectionGain = collectionGain,
        uptime = uptime,
        customImage = customImage,
        textColor = textColor,
        profileID = profileID
    )
}
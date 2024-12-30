package com.dergruenkohl.wheatwebsite.database.dao


import com.dergruenkohl.wheatwebsite.database.outgoing.*
import com.dergruenkohl.wheatwebsite.database.table.*
import com.dergruenkohl.wheatwebsite.getMinecraftUsername
import com.dergruenkohl.wheatwebsite.service.Guild
import com.dergruenkohl.wheatwebsite.service.Member
import com.dergruenkohl.wheatwebsite.service.time
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

// Guild DAO
class GuildEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GuildEntity>(GuildTable)

    var guildID by GuildTable.guildID
    var lastUpdate by GuildTable.lastUpdate
    val members by MemberEntity referrersOn MemberTable.guild

    suspend fun toGuild(): Guild = Guild(
        guildID = guildID,
        lastUpdate = lastUpdate,
        members = members.map { it.toMember() }
    )
}

// Member DAO
class MemberEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MemberEntity>(MemberTable)

    var uuid by MemberTable.uuid
    var guild by GuildEntity referencedOn MemberTable.guild
    val expHistory by ExpHistoryEntity referrersOn ExpHistoryTable.member
    suspend fun toMember(): Member = Member(
        uuid = uuid,
        name = getMinecraftUsername(uuid),
        expHistory = expHistory.associate { it.timestamp to it.toTime() } // Convert to LinkedHashMap<Long, time>
            .toMap(LinkedHashMap())
    )
}

// ExpHistory DAO
class ExpHistoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ExpHistoryEntity>(ExpHistoryTable)

    var member by MemberEntity referencedOn ExpHistoryTable.member
    var timestamp by ExpHistoryTable.timestamp
    var hours by ExpHistoryTable.hours
    var mins by ExpHistoryTable.mins

    fun toTime(): time = time(hours = hours, mins = mins)
}

class HistoricalMemberEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<HistoricalMemberEntity>(HistoricalMemberTable)

    var uuid by HistoricalMemberTable.uuid
    val expHistory by HistoricalExpHistoryEntity referrersOn HistoricalExpHistoryTable.member

    suspend fun toMember(): Member = Member(
        uuid = uuid,
        name = getMinecraftUsername(uuid),
        expHistory = expHistory
            .associate { it.timestamp to it.toTime() } // Convert to LinkedHashMap<Long, time>
            .toMap(LinkedHashMap())
    )
}

// ExpHistory DAO
class HistoricalExpHistoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<HistoricalExpHistoryEntity>(HistoricalExpHistoryTable)

    var member by HistoricalMemberEntity referencedOn HistoricalExpHistoryTable.member
    var timestamp by HistoricalExpHistoryTable.timestamp
    var hours by HistoricalExpHistoryTable.hours
    var mins by HistoricalExpHistoryTable.mins

    fun toTime(): time = time(hours = hours, mins = mins)
}

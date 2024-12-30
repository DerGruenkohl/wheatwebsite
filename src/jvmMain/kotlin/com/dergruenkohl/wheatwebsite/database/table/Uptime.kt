package com.dergruenkohl.wheatwebsite.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

// Guild table definition
object GuildTable : LongIdTable("guilds") {
    val guildID: Column<String> = varchar("guild_id", 50).uniqueIndex()
    val lastUpdate: Column<Long> = long("last_update")
}

// Member table definition
object MemberTable : LongIdTable("members") {
    val uuid: Column<String> = varchar("uuid", 36)
    val guild = reference("guild_id", GuildTable)
}

// ExpHistory table definition
object ExpHistoryTable : IntIdTable("exp_history") {
    val member = reference("member_id", MemberTable)
    val timestamp: Column<Long> = long("timestamp")
    val hours: Column<Int> = integer("hours")
    val mins: Column<Int> = integer("mins")
}
object HistoricalMemberTable: LongIdTable("historical_members") {
    val uuid: Column<String> = varchar("uuid", 36)
}
object HistoricalExpHistoryTable: IntIdTable("historical_history") {
    val member = reference("member_id", HistoricalMemberTable)
    val timestamp: Column<Long> = long("timestamp")
    val hours: Column<Int> = integer("hours")
    val mins: Column<Int> = integer("mins")
}

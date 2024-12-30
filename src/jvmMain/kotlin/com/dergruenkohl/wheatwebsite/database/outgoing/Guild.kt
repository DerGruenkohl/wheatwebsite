package com.dergruenkohl.wheatwebsite.database.outgoing

import com.dergruenkohl.wheatwebsite.getExpHistory
import kotlinx.serialization.Serializable
import net.hypixel.api.reply.GuildReply

fun transformGuildMember(members: List<GuildReply.Guild.Member>): MutableList<GuildEntry> {
    val entrys = mutableListOf<GuildEntry>()
    members.forEach {
        entrys.add(GuildEntry(
            it.uuid.toString(),
            it.rank,
            it.joinDate.toEpochSecond(),
            it.getExpHistory()
        ))
    }
    return entrys
}

@Serializable
data class GuildEntry(
    val uuid: String,
    val rank: String,
    val joined: Long,
    val expHistory: LinkedHashMap<Long, Int>
)
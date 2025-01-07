package com.dergruenkohl.wheatwebsite.hypixel

import com.dergruenkohl.wheatwebsite.*
import com.dergruenkohl.wheatwebsite.Config.apikey
import com.dergruenkohl.wheatwebsite.service.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.hypixel.api.HypixelAPI
import net.hypixel.api.reactor.ReactorHttpClient
import net.hypixel.api.reply.GuildReply
import java.util.*

object ApiHandler {
    private val client by lazy {
        ReactorHttpClient(UUID.fromString(apikey))
    }

    private val hypixelAPI by lazy {
        HypixelAPI(client)
    }

    fun getGuildMembers(name: String): List<GuildReply.Guild.Member> {
        return hypixelAPI.getGuildByName(name).get().guild.members
    }

    fun getSelectedProfile(name: String): String {
        hypixelAPI.getSkyBlockProfiles(name).get().profiles.forEach {

            if (it.asJsonObject["selected"].asBoolean) {
                println(it.asJsonObject.keySet())
                return it.asJsonObject["profile_id"].asString
            }
        }
        return ""
    }
    suspend fun getPlayerExpHistory(ign: String): expHistory? {
        val uuid = getMinecraftUUID(ign)
        withContext(Dispatchers.IO) {
            hypixelAPI.getGuildByPlayer(uuid).get()
        }.guild?.let {
           it.members.forEach {
               if (it.uuid.toString().replace("-", "") == uuid.replace("-", "")) {
                   return it.getFarmingUptime()
               }
           }
        }
        return null
    }

    suspend fun getExpHistoryFromApi(id: String): Guild? {
        println("apiHistory call")
        hypixelAPI.getGuildById(id).get().guild?.let {
            println(it.name)
            val guild = createGuild(it.members, it.id)
            return guild
        }
        return null
    }

    fun getGuildMembersPlayer(name: String): List<GuildReply.Guild.Member> {
        return hypixelAPI.getGuildByPlayer(name).get().guild.members
    }

    fun getGuildID(name: String): String {
        return hypixelAPI.getGuildByName(name).get().guild.id
    }

    fun getGuildIDPlayer(name: String): String {
        return hypixelAPI.getGuildByPlayer(name).get().guild.id
    }

    suspend fun getGuild(name: String): String {
        return withContext(Dispatchers.IO) {
            hypixelAPI.getGuildByPlayer(getMinecraftUUID(name)).get()
        }.guild.name
    }

    fun getGuildByUUID(name: String): String {
        return hypixelAPI.getGuildByPlayer(name).get().guild.name
    }

    fun getLinkedDiscord(name: String): String {
        return hypixelAPI.getPlayerByUuid(name)
            .get().player.getObjectProperty("socialMedia")["links"].asJsonObject["DISCORD"].asString
    }

    /*fun getSbStats(name: String): String {
        hypixelAPI.getSkyBlockProfiles(name).get().profiles.forEach {
            if (it.asJsonObject["selected"].asBoolean) {
                val profile = it.asJsonObject["members"].asJsonObject[name].asJsonObject
                val skills = profile["player_data"].asJsonObject["experience"].asJsonObject.toString()
                val parsedSkills = Json.decodeFromString<HypixelSkills>(skills)
                println(parsedSkills)
                return it.asJsonObject["profile_id"].asString
            }
        }
        return ""

    }*/

    private fun createGuild(members: List<GuildReply.Guild.Member>, guildID: String): Guild {
        val newMembers = mutableListOf<Member>()
        members.forEach {
            newMembers.add(
                Member(
                    it.uuid.toString(),
                    "",
                    it.getFarmingUptime()
                )
            )
        }
        return Guild(
            guildID,
            System.currentTimeMillis(),
            newMembers
        )
    }
}
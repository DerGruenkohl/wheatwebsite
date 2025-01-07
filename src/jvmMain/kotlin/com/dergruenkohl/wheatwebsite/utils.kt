package com.dergruenkohl.wheatwebsite

import com.dergruenkohl.wheatwebsite.service.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import net.hypixel.api.reply.GuildReply
import reactor.netty.http.client.HttpClient
import java.io.IOException
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.floor

@Serializable
data class MojangResponse(
    val name: String,
    val id: String
)

suspend fun getMinecraftUsername(uuid: String): String = Api.client.request("https://mowojang.matdoes.dev/$uuid").body<MojangResponse>().name
suspend fun getMinecraftUUID(ign: String): String = Api.client.request("https://mowojang.matdoes.dev/$ign").body<MojangResponse>().id

fun GuildReply.Guild.Member.getExpHistory(): LinkedHashMap<Long, Int>{
    val map = LinkedHashMap<Long, Int>()
    val field = this::class.java.getDeclaredField("weeklyExperience")
    field.isAccessible = true
    val value = field.get(this) as Map<String, Int>
    val f = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    value.forEach { time, exp ->
        val date = LocalDate.parse(time, f).toEpochDay()
        map[date] = exp
    }

    return map
}
fun GuildReply.Guild.Member.getFarmingUptime(): expHistory{
    val map = this.getExpHistory()
    val newMap = expHistory()
    map.forEach {
        val hours = it.value/9000f
        val hoursInt = floor(hours).toInt()
        val minutes = ((hours - hoursInt) * 60).toInt()
        val pair = time(hoursInt, minutes)
        newMap[it.key] = pair
    }
    return newMap
}
fun <V> filterByDay(map: Map<Long, V>): Map<Long, V> {
    // Define a date formatter to extract the date part from the timestamp
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("America/New_York"))

    // Group the entries by day
    val groupedByDay = map.entries.groupBy { entry ->
        val date = Instant.ofEpochMilli(entry.key).atZone(ZoneId.of("America/New_York")).toLocalDate()
        formatter.format(date)
    }

    // Find the entry with the highest timestamp for each day
    val filteredEntries = groupedByDay.mapValues { (_, entries) ->
        entries.maxByOrNull { it.key }!!
    }

    // Convert back to a map with only the entries having the highest timestamp per day
    return filteredEntries.map { it.value.key to it.value.value }.toMap()
}

fun Double.toLevel(): Int {
    val levelThresholds = listOf(
        0L, 50L, 175L, 375L, 675L, 1175L, 1925L, 2925L, 4425L, 6425L,
        9925L, 14925L, 22425L, 32425L, 47425L, 67425L, 97425L, 147425L,
        222425L, 322425L, 522425L, 822425L, 1222425L, 1722425L, 2322425L,
        3022425L, 3822425L, 4722425L, 5722425L, 6822425L, 8022425L, 9322425L,
        10722425L, 12222425L, 13822425L, 15522425L, 17322425L, 19222425L,
        21222425L, 23322425L, 25522425L, 27822425L, 30222425L, 32722425L,
        35322425L, 38072425L, 40972425L, 44072425L, 47472425L, 51172425L,
        55172425L, 59472425L, 64072425L, 68972425L, 74172425L, 79672425L,
        85472425L, 91572425L, 97972425L, 104672425L, 111672425L
    )

    // Find the corresponding level
    for ((level, threshold) in levelThresholds.withIndex()) {
        if (this < threshold) {
            return level - 1
        }
    }
    return levelThresholds.size - 1
}

/*fun filterByDay(input: List<Player>): List<Player>{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("America/New_York"))
    val group = input.groupBy {
        val date = Instant.ofEpochMilli(it.timestamp).atZone(ZoneId.of("America/New_York")).toLocalDate()
        formatter.format(date)
    }
    val filteredEntries = group.mapValues { (_, entries) ->
        entries.maxByOrNull { it.timestamp}!!
    }
    return filteredEntries.values.toList()
}*/
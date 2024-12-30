package com.dergruenkohl.wheatwebsite

import com.dergruenkohl.wheatwebsite.service.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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


@Throws(IOException::class)
suspend fun getMinecraftUsername(uuid: String): String {
    val client = HttpClient.create()
    val url = "https://mowojang.matdoes.dev/$uuid"
    val response = withContext(Dispatchers.IO) {
        client.get()
            .uri(url)
            .responseContent()
            .aggregate()
            .asString()
            .block()
    }!!
    return Json.parseToJsonElement(response).jsonObject["name"].toString().replace("\"", "")
}

@Throws(IOException::class)
suspend fun getMinecraftUUID(name: String): String {
    val client = HttpClient.create()
    val url = "https://mowojang.matdoes.dev/$name"
    val response = withContext(Dispatchers.IO) {
        client.get()
            .uri(url)
            .responseContent()
            .aggregate()
            .asString()
            .block()
    }!!
    return Json.parseToJsonElement(response).jsonObject["id"].toString().replace("\"", "")
}
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
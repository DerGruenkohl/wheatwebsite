package com.dergruenkohl.wheatwebsite.database.outgoing


import com.dergruenkohl.wheatwebsite.getMinecraftUsername
import com.dergruenkohl.wheatwebsite.service.time
import kotlinx.serialization.Serializable

@Serializable
data class Leaderboard(
    val size: Int,
    val members: Map<String, time>
)
@Serializable
data class LbPage(
    val totalPages: Int,
    val players: Map<String, time>,
)

suspend fun Leaderboard.getPage(pageIndex: Int): LbPage {
    val paginated = this.members.entries
        .chunked(10) // Break the entries into chunks of size `pageSize`

    val mapped = paginated.getOrNull(pageIndex)!!.map {
        getMinecraftUsername(it.key) to it.value
    }

    return LbPage(
        paginated.size,
        mapped.toMap()
    )
}
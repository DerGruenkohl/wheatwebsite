package com.dergruenkohl.wheatwebsite.service

import kotlinx.serialization.Serializable
import kotlin.math.floor

@Serializable
data class Guild(
    val guildID: String,
    val lastUpdate: Long,
    val members: List<Member>
)
@Serializable
data class Member(
    val uuid: String,
    val name: String,
    val expHistory: expHistory
)
typealias trackedMember = Member
typealias expHistory = LinkedHashMap<Long, time>
typealias lbHistory = HashMap<Long, time>
@Serializable
data class time(
    val hours: Int,
    val mins: Int
) : Comparable<time> {
    override fun compareTo(other: time): Int {
        return compareValuesBy(this, other, time::hours, time::mins)
    }
    override fun toString(): String {
        return "${this.hours}h, ${this.mins}m"
    }
    fun toMinutes(): Int = this.hours * 60 + this.mins
    fun toHours(): Double = this.hours + this.mins/60.0
}
fun Member.getAverage(): time{
    var totalhours = 0
    var totalmins = 0
    this.expHistory.forEach {
        totalmins += it.value.mins
        totalhours += it.value.hours
    }
    totalhours += floor(totalmins/60f).toInt()

    val avghrs = totalhours /7f
    val hoursInt = floor(avghrs).toInt()
    val minutes = ((avghrs - hoursInt) * 60).toInt()

    return time(hoursInt, minutes)
}

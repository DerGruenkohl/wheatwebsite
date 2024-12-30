package com.dergruenkohl.wheatwebsite.database.dao


import com.dergruenkohl.wheatwebsite.database.table.LbHistoryTable
import com.dergruenkohl.wheatwebsite.service.time
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class LbHistoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LbHistoryEntity>(LbHistoryTable)

    var timestamp by LbHistoryTable.timestamp
    var hours by LbHistoryTable.hours
    var mins by LbHistoryTable.mins

    fun toTime(): Pair<Long, time> = timestamp to time(hours = hours, mins = mins)
}
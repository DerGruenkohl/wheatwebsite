package com.dergruenkohl.wheatwebsite.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object LbHistoryTable : IntIdTable("lb_history") {
    val timestamp: Column<Long> = long("timestamp")
    val hours: Column<Int> = integer("hours")
    val mins: Column<Int> = integer("mins")
}

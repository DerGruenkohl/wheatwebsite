package com.dergruenkohl.wheatwebsite.components

import com.dergruenkohl.wheatwebsite.service.Member
import com.dergruenkohl.wheatwebsite.models.Uptime
import io.kvision.core.*
import io.kvision.form.getDataWithFileContent
import io.kvision.html.*
import io.kvision.panel.HPanel
import io.kvision.panel.splitPanel
import io.kvision.table.*
import io.kvision.table.Table
import io.kvision.utils.px
import kotlinx.datetime.LocalDate
import kotlin.js.Date


suspend fun uptimeComponent(ign: String): Div {
    val member = Uptime.getUptime(ign)
    val table = buildTable(member)
    return Div{
        image("https://crafatar.com/renders/body/${member.uuid}"){
            width = 150.px
            height = 300.px
        }
        span("Uptime of ${member.name}"){
            fontSize = 32.px
        }
        add(table)
    }
}
fun buildTable(member: Member): Table {

    val table = Table(
        listOf("Date", "Uptime"),
        setOf(TableType.BORDERED, TableType.SMALL, TableType.STRIPED, TableType.HOVER),
        responsiveType = ResponsiveType.RESPONSIVE
    ){
        this.maxWidth = 500.px
        this.alignSelf = AlignItems.CENTER
        member.expHistory.forEach {
            row {
                cell(LocalDate.fromEpochDays(it.key.toInt()).toString())
                cell(it.value.toString())
            }
        }
    }
    return table
}


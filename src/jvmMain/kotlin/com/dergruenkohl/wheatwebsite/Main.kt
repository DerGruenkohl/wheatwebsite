package com.dergruenkohl.wheatwebsite

import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.routing.*
import io.kvision.remote.applyRoutes
import io.kvision.remote.getAllServiceManagers
import io.kvision.remote.kvisionInit
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.dergruenkohl.wheatwebsite.service.*
import com.dergruenkohl.wheatwebsite.service.profile.ProfileService

fun Application.main() {
    install(Compression)
    routing {
        getAllServiceManagers().forEach { applyRoutes(it) }
    }
    val module = module {
        factoryOf(::PingService)
        factoryOf(::UptimeService)
        factoryOf(::ProfileService)
    }
    kvisionInit(module)
}

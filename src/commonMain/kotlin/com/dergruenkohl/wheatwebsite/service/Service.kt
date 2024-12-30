package com.dergruenkohl.wheatwebsite.service

import io.kvision.annotations.KVService

@KVService
interface IPingService {
    suspend fun ping(message: String): String
}
@KVService
interface IUptimeService {
    suspend fun uptime(ign: String): Member
}
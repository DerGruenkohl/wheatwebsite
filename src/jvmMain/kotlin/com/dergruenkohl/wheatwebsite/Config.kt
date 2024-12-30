package com.dergruenkohl.wheatwebsite

import java.io.File
import java.util.*

object Config {
    private val properties: Properties = Properties()

    init {
        val configFile = File("config.properties")
        if (configFile.exists()) {
            properties.load(configFile.inputStream())
        } else {
            throw IllegalStateException("Config file not found: ${configFile.absolutePath}")
        }
    }

    val dburl: String get() = properties.getProperty("dburl")
    val testdb: String get() = properties.getProperty("testdb")
    val dbuser: String get() = properties.getProperty("dbuser")
    val dbpw: String get() = properties.getProperty("dbpw")
    val test: Boolean get() = properties.getProperty("test", "false").toBoolean()
    val apikey: String get() = properties.getProperty("apikey")
    val webhook: String get() = properties.getProperty("webhook")
    val key: String get() = properties.getProperty("key")
}

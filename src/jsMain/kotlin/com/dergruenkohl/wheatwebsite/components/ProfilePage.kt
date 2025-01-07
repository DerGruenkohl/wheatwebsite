package com.dergruenkohl.wheatwebsite.components

import com.dergruenkohl.wheatwebsite.models.Profile
import io.kvision.html.Div
import io.kvision.panel.vPanel

object ProfilePage: Div() {
    suspend inline fun setup(ign: String): ProfilePage {
        remove()

        add(Div("Profile page for $ign"))
        add(uptimeComponent(ign))
        add(profilePageComponent(ign))
        return this
    }
    fun remove(){
        this.removeAll()
        this.parent?.remove(this)
    }

    suspend fun profilePageComponent(ign: String): Div {
        val profile = Profile.getProfile(ign)
        return Div {
            add(Div("Username: ${profile.ign}"))
            add(Div("UUID: ${profile.uuid}"))
            add(Div("Skills:"))
            profile.sb.skills.let { skills ->
                add(Div("Combat: ${skills.combat}"))
                add(Div("Mining: ${skills.mining}"))
                add(Div("Foraging: ${skills.foraging}"))
                add(Div("Fishing: ${skills.fishing}"))
                add(Div("Enchanting: ${skills.enchanting}"))
                add(Div("Alchemy: ${skills.alchemy}"))
                add(Div("Taming: ${skills.taming}"))
                add(Div("Carpentry: ${skills.carpentry}"))
                add(Div("Runecrafting: ${skills.runecrafting}"))
                add(Div("Social: ${skills.social}"))
                add(Div("Farming: ${skills.farming}"))
            }
        }
    }
}
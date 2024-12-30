package com.dergruenkohl.wheatwebsite.components

import io.kvision.html.Div

object ProfilePage: Div() {
    suspend inline fun setup(ign: String): ProfilePage {
        remove()

        add(Div("Profile page for $ign"))
        add(uptimeComponent(ign))
        return this
    }
    fun remove(){
        this.removeAll()
        this.parent?.remove(this)
    }
}
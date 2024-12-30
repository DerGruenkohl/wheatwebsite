package com.dergruenkohl.wheatwebsite.components

import io.kvision.core.*
import io.kvision.form.text.textInput
import io.kvision.html.button
import io.kvision.panel.HPanel
import io.kvision.utils.px



object Searchbar: HPanel(){
    init {
        val input = textInput {
            placeholder = "Enter an ign"
            maxWidth = 300.px
            maxlength = 16
        }
        spacing = 10
        this.alignSelf = AlignItems.CENTER
        button("Search") {
            onClickLaunch {
                getRoot()?.let {
                    input.value?.let { search ->
                        println("searching for $search")
                        it.add(ProfilePage.setup(search))
                    }
                }
            }
        }
    }
}




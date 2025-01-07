package com.dergruenkohl.wheatwebsite.models

import com.dergruenkohl.wheatwebsite.service.IUptimeService
import com.dergruenkohl.wheatwebsite.service.Member
import com.dergruenkohl.wheatwebsite.service.profile.IProfileService
import com.dergruenkohl.wheatwebsite.service.profile.ProfileReply
import io.kvision.remote.getService

object Profile {
    private val profileService = getService<IProfileService>()

    suspend fun getProfile(ign: String): ProfileReply {
        return profileService.getProfile(ign)
    }
}
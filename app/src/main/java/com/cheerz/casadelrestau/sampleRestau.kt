package com.cheerz.casadelrestau

import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData

object sampleRestau {

    fun getSample(): List<MiamzReqPlaceData> {
        val user  = listOf<String>("jean","mathieu", "michael", "toto", "tutu" )
        val test = MiamzReqPlaceData("nom", 12.0, 12.0, "path", "path2", "path",
            "path", "path", "Categorie", "User email", "12:30 PM - 14:00",user )
        val test2 = MiamzReqPlaceData("nom2", 12.0, 12.0, "path", "path2", "path",
            "path", "path", "Categorie2", "User email2","12:30 PM - 14:00", user )
        val test3 = MiamzReqPlaceData("nom3", 12.0, 12.0, "path", "path2", "path",
            "path", "path", "Categorie3", "User email3", "12:30 PM - 14:00", user)
        val places: MutableList<MiamzReqPlaceData> = mutableListOf(test, test2, test3)
        return places
    }
}
package com.example.matchescard.data

import com.example.matchescard.room.Person
import com.google.gson.annotations.SerializedName


data class Results (

    @SerializedName("gender"     ) var gender     : String?    = null,
    @SerializedName("name"       ) var name       : Name?       = Name(),
    @SerializedName("location"   ) var location   : Location?   = Location(),
    @SerializedName("email"      ) var email      : String?     = null,
    @SerializedName("login"      ) var login      : Login?      = Login(),
    @SerializedName("dob"        ) var dob        : Dob?        = Dob(),
    @SerializedName("registered" ) var registered : Registered? = Registered(),
    @SerializedName("phone"      ) var phone      : String?     = null,
    @SerializedName("cell"       ) var cell       : String?     = null,
    @SerializedName("id"         ) var id         : Id         = Id(),
    @SerializedName("picture"    ) var picture    : Picture?    = Picture(),
    @SerializedName("nat"        ) var nat        : String?     = null
){
    fun toPerson():Person{
        return Person(
            name = "${name?.first} ${name?.last}",
            age = dob?.age,
            gender = gender!!,
            imageUrl = picture?.medium!!,
            imageUrlBig = picture?.large!!,
            location = location.toString(),
            country = location?.country!!,
            phoneNo = phone!!
        )
    }
}
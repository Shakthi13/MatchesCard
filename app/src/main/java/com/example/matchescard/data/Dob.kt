package com.example.matchescard.data

import com.google.gson.annotations.SerializedName


data class Dob (

  @SerializedName("date" ) var date : String? = null,
  @SerializedName("age"  ) var age  : String?    = null

)
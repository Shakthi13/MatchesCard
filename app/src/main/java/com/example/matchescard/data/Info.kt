package com.example.matchescard.data

import com.google.gson.annotations.SerializedName


data class Info (

  @SerializedName("seed"    ) var seed    : String? = null,
  @SerializedName("results" ) var results : String?    = null,
  @SerializedName("page"    ) var page    : String?    = null,
  @SerializedName("version" ) var version : String? = null

)
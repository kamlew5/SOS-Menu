package com.example.menu.serializers

import com.google.gson.annotations.SerializedName

data class SubPatientList (
    @SerializedName("pacjent_id") val pacjent_id : Int,
    @SerializedName("pacjent_imie_nazwisko") val pacjent_imie_nazwisko : String
)

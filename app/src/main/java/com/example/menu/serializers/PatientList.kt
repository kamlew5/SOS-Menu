package com.example.menu.serializers

import com.google.gson.annotations.SerializedName

data class PatientList (

    @SerializedName("data") val data : List<SubPatientList>
)
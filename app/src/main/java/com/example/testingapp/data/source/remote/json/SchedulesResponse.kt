package com.example.testingapp.data.source.remote.json

import com.example.testingapp.model.Main
import com.squareup.moshi.Json

data class SchedulesResponse(
    @Json(name = "events")
    val events: List<Main?>?
)
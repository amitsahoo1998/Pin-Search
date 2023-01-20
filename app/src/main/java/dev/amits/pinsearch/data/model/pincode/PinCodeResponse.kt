package dev.amits.pinsearch.data.model.pincode

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PinCodeResponse(
    val _shards: Shards,
    val hits: Hits,
    val timed_out: Boolean,
    val took: Int
) {
    data class Shards(
        val failed: Int,
        val skipped: Int,
        val successful: Int,
        val total: Int
    )

    data class Hits(
        val hits: List<Hit>,
        val max_score: Double,
        val total: Total
    ) {
        data class Hit(
            val _id: String,
            val _index: String,
            val _score: Double,
            val _source: Source,
            val _type: String
        ) {
            @Entity(tableName = "pinTable")
            data class Source(
                val City: String? ,
                val D_flag: String? ,
                val District: String?,
                val Pincode: String?,
                val State: String?,
                @PrimaryKey
                val Street: String,
                val Talukka: String?,
                val country: String?,
                val gst_state_code: String?,
                val id: Int?,
                val state_code: String?,
                val status: Int?
            )
        }

        data class Total(
            val relation: String,
            val value: Int
        )
    }
}
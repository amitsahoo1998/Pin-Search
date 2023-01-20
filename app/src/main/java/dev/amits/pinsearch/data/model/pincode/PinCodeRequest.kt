package dev.amits.pinsearch.data.model.pincode

data class PinCodeRequest(
    val query: Query
) {
    data class Query(
        val bool: Bool
    ) {
        data class Bool(
            val filter: List<Filter>
        ) {
            data class Filter(
                val match: Match
            ) {
                data class Match(
                    val Pincode: String
                )
            }
        }
    }
}
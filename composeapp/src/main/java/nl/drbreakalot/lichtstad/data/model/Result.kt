package nl.drbreakalot.lichtstad.data.model

data class Result(var key: String, val image: String?, val title: String, val url: String) {
    // No-arg constructor is required for automatic firebase mapping
    constructor() : this("", null, "", "")
}

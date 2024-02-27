package com.example.practic

import java.time.LocalDateTime

class TimeParser {
    private val map = mapOf(
        1 to "January",
        2 to "February",
        3 to "March",
        4 to "April",
        5 to "May",
        6 to "June",
        7 to "July",
        8 to "August",
        9 to "September",
        10 to "October",
        11 to "November",
        12 to "December"
    )
    fun getTimeNow(): String {
        var month = ""
            for(value in map) {
                when (LocalDateTime.now().month.value) {
                    value.key -> month = value.value
                }
        /*when (LocalDateTime.now().month.value) {
            1 -> month = context.resources.getString(R.string.january)
            2 -> month = context.resources.getString(R.string.february)
            3 -> month = context.resources.getString(R.string.march)
            4 -> month = context.resources.getString(R.string.april)
            5 -> month = context.resources.getString(R.string.may)
            6 -> month = context.resources.getString(R.string.june)
            7 -> month = context.resources.getString(R.string.july)
            8 -> month = context.resources.getString(R.string.august)
            9 -> month = context.resources.getString(R.string.september)
            10 -> month = context.resources.getString(R.string.october)
            11 -> month = context.resources.getString(R.string.november)
            12 -> month = context.resources.getString(R.string.december)
            else -> {
                month = ""
            }*/
        }
        var minutes: String = LocalDateTime.now().toLocalTime().minute.toString()
        when (minutes) {
            "0" -> minutes = "00"
            "1" -> minutes = "01"
            "2" -> minutes = "02"
            "3" -> minutes = "03"
            "4" -> minutes = "04"
            "5" -> minutes = "05"
            "6" -> minutes = "06"
            "7" -> minutes = "07"
            "8" -> minutes = "08"
            "9" -> minutes = "09"
        }
        val hourMinutes: String = LocalDateTime.now().hour.plus(3).toString() + ":" + minutes
        return LocalDateTime.now().dayOfMonth.toString() + " " + month + " " + hourMinutes
    }
}
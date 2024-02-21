package com.example.practic

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.time.LocalDateTime

class TimeParser {
    @Composable
    fun getTimeNow(): String {
        val month: String
        when (LocalDateTime.now().month.value) {
            1 -> month = stringResource(R.string.january)
            2 -> month = stringResource(R.string.february)
            3 -> month = stringResource(R.string.march)
            4 -> month = stringResource(R.string.april)
            5 -> month = stringResource(R.string.may)
            6 -> month = stringResource(R.string.june)
            7 -> month = stringResource(R.string.july)
            8 -> month = stringResource(R.string.august)
            9 -> month = stringResource(R.string.september)
            10 -> month = stringResource(R.string.october)
            11 -> month = stringResource(R.string.november)
            12 -> month = stringResource(R.string.december)
            else -> {
                month = ""
            }
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
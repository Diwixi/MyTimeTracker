package com.diwixis.mytimetracker


/**
 * @author П. Густокашин (Diwixis)
 */
data class TimerNote(
    var startTime: Long,
    var endTime: Long,
    var description: String
)
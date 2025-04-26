package com.whittingtontech.accounting

import java.time.*
import java.util.Locale

data class TimesheetEntry(
        val payrollItem: PayrollItem,
        val serviceItem: ServiceItem?,
        val customerJob: CustomerJob?,
        val hours: TimesheetEntryHours,
        val billable: Boolean
) {
    fun totalHours(): Duration = hours.total()
}

data class Timesheet(
        val belongsTo: Person,
        val sheetStart: LocalDate,
        val sheetEnd: LocalDate,
        val entries: MutableList<TimesheetEntry>,
        val totalHours: TimesheetEntryHours
)

data class TimesheetEntryHours(val dailyHours: Map<DayOfWeek, Duration>) {
    fun total(): Duration {
        var t: Duration = Duration.ofHours(0)
        for (v in dailyHours.values) {
            t = v.plus(t)
        }
        return t
    }
}

data class Person(
        val first: String,
        val last: String,
        val honorific: String? = null,
        val middleInitial: Char? = null,
        val title: String? = null
) {
    fun displayName(locale: Locale = Locale.getDefault()): String =
            buildString {
                        honorific?.let { append(it).append(' ') }
                        append(first.capitalize(locale)).append(' ')
                        middleInitial?.let { append(it).append(". ") }
                        append(last.capitalize(locale))
                    }
                    .trim()
}

enum class PayrollItem {
    L1_TECH,
    L2_TECH,
    L3_TECH,
    ADMIN
}

enum class ServiceItem {
    ONSITE_SUPPORT,
    REMOTE_SUPPORT,
    TRAINING,
    ADMIN
}

data class CustomerJob(
        val id: String,
        val name: String,
        val address: String,
        val contact: String?,
        val type: JobType
)

enum class JobType {
    COMMERCIAL,
    RESIDENTIAL
}

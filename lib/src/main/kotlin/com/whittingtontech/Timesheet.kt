package com.whittingtontech.accounting

import java.util.Date

interface TimesheetEntry {
    val payrollItem: PayrollItem
    val serviceItem: ServiceItem?
    val customerJob: CustomerJob?
    val hours: TimesheetEntryHours
    fun totalHours(): Double
    val billable: Boolean
}

interface Timesheet {
    val belongsTo: Name
    val sheetStart: Date
    val sheetEnd: Date
    val entries: MutableList<TimesheetEntry>
    val totalHours: TimesheetEntryHours
}

interface TimesheetEntryHours {
    val dailyHours: MutableMap<DayOfWeek, Double>
    fun total(): Double
}

interface Name {
    val first: String
    val last: String
    val honorific: String?
    val middleInitial: Char?
    val title: String?
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

interface CustomerJob {
    val id: String
    val name: String
    val address: String
    val contact: String?
    val type: JobType
}

enum class DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

enum class JobType {
    COMMERCIAL,
    RESEDENTIAL
}

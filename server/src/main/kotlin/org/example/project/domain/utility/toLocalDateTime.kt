package org.example.project.domain.utility

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.OffsetDateTime

/**
 * Converts a string to a LocalDateTime
 * @return [LocalDateTime] LocalDateTimeを返します
 */
fun String.toLocalDateTime(): LocalDateTime {
    val offsetDateTime = OffsetDateTime.parse(this)
    val localDateTime = offsetDateTime.toLocalDateTime().toKotlinLocalDateTime()
    return localDateTime
}

package com.example.projecttwo
import java.util.UUID
data class Reminder(
    val id: UUID,
    val title: String,
    val dueDate: String,
    val location: String,
    val completed: Boolean,
    val notes: String
)
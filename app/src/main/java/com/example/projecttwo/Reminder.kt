package com.example.projecttwo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
@Entity
data class Reminder(
    @PrimaryKey val id: UUID,
    val title: String,
    val dueDate: String,
    val location: String,
    val completed: Boolean,
    val notes: String
)
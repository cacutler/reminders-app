package com.example.projecttwo
import androidx.lifecycle.ViewModel
import java.util.UUID
class ReminderListViewModel: ViewModel() {
    val reminders = mutableListOf<Reminder>()
    init {
        for (i in 1 until 31) {
            val reminder = Reminder(
                id = UUID.randomUUID(),
                title = "Reminder #$i",
                dueDate = "3/$i/2024",
                completed = i % 2 == 0,
                notes = "Testing reminder #$i",
                location = "Home"
            )
            reminders += reminder
        }
    }
}
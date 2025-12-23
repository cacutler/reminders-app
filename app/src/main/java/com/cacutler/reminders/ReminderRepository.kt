package com.cacutler.reminders
import android.content.Context
import androidx.room.Room
import database.ReminderDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.util.UUID
private const val DATABASE_NAME = "reminder-database"
class ReminderRepository private constructor(context: Context, private val coroutineScope: CoroutineScope = GlobalScope) {
    private val database: ReminderDatabase = Room.databaseBuilder(context.applicationContext, ReminderDatabase::class.java, DATABASE_NAME).build()
    fun getReminders(): Flow<List<Reminder>> = database.reminderDao().getReminders()
    suspend fun getReminder(id: UUID): Reminder = database.reminderDao().getReminder(id)
    fun updateReminder(reminder: Reminder) {
        coroutineScope.launch {
            database.reminderDao().updateReminder(reminder)
        }
    }
    suspend fun addReminder(reminder: Reminder) {
        database.reminderDao().addReminder(reminder)
    }
    companion object {
        private var INSTANCE: ReminderRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ReminderRepository(context)
            }
        }
        fun get(): ReminderRepository {
            return INSTANCE ?: throw IllegalStateException("ReminderRepository must be initialized")
        }
    }
}
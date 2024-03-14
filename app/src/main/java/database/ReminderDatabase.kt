package database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projecttwo.Reminder
@Database(entities = [Reminder::class], version = 1)
abstract class ReminderDatabase: RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}
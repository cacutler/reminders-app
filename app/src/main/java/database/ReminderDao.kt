package database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cacutler.reminders.Reminder
import kotlinx.coroutines.flow.Flow
import java.util.UUID
@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder")
    fun getReminders(): Flow<List<Reminder>>
    @Query("SELECT * FROM reminder WHERE id=(:id)")
    suspend fun getReminder(id: UUID): Reminder
    @Update
    suspend fun updateReminder(reminder: Reminder)
    @Insert
    suspend fun addReminder(reminder: Reminder)
}
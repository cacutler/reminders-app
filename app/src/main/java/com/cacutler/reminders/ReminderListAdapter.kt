package com.cacutler.reminders
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cacutler.reminders.databinding.ListItemReminderBinding
import java.util.UUID
class ReminderHolder(private val binding: ListItemReminderBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(reminder: Reminder, onReminderClicked: (reminderId: UUID) -> Unit) {
        binding.reminderLocation.text = reminder.location
        binding.reminderTitle.text = reminder.title
        binding.reminderDueDate.text = reminder.dueDate
        binding.root.setOnClickListener {
            onReminderClicked(reminder.id)
        }
        binding.reminderCompleted.visibility = if (reminder.completed) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
class ReminderListAdapter(private val reminders: List<Reminder>, private val onReminderClicked: (reminderId: UUID) -> Unit): RecyclerView.Adapter<ReminderHolder>() {
    override fun getItemCount() = reminders.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemReminderBinding.inflate(inflater, parent, false)
        return ReminderHolder(binding)
    }
    override fun onBindViewHolder(holder: ReminderHolder, position: Int) {
        val reminder = reminders[position]
        holder.apply {
            holder.bind(reminder, onReminderClicked)
        }
    }
}
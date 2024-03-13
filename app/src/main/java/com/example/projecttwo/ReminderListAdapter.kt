package com.example.projecttwo
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttwo.databinding.ListItemReminderBinding
class ReminderHolder(
    private val binding: ListItemReminderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(reminder: Reminder) {
        binding.reminderLocation.text = reminder.location
        binding.reminderTitle.text = reminder.title
        binding.reminderDueDate.text = reminder.dueDate
        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${reminder.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
class ReminderListAdapter(
    private val reminders: List<Reminder>
): RecyclerView.Adapter<ReminderHolder>() {
    override fun getItemCount() = reminders.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemReminderBinding.inflate(inflater, parent, false)
        return ReminderHolder(binding)
    }
    override fun onBindViewHolder(holder: ReminderHolder, position: Int) {
        val reminder = reminders[position]
        holder.apply {
            holder.bind(reminder)
        }
    }
}
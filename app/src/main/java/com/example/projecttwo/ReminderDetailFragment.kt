package com.example.projecttwo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.projecttwo.databinding.FragmentReminderDetailBinding
class ReminderDetailFragment: Fragment() {
    private var _binding: FragmentReminderDetailBinding? = null
    private lateinit var reminder: Reminder
    private val binding
        get() = checkNotNull(_binding) {
            "Cannon access binding because it is null.  Is the view visible?"
        }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReminderDetailBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            reminderTitle.doOnTextChanged { text, _, _, _ ->
                reminder = reminder.copy(title = text.toString())
            }
            reminderLocation.doOnTextChanged { text, _, _, _ ->
                reminder = reminder.copy(location = text.toString())
            }
            reminderNotes.doOnTextChanged { text, _, _, _ ->
                reminder = reminder.copy(notes = text.toString())
            }
            reminderDueDate.doOnTextChanged { text, _, _, _ ->
                reminder = reminder.copy(dueDate = text.toString())
            }
            reminderCompleted.setOnCheckedChangeListener { _, isChecked ->
                reminder = reminder.copy(completed = isChecked)
            }
        }
    }
}
package com.example.projecttwo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.projecttwo.databinding.FragmentReminderDetailBinding
import kotlinx.coroutines.launch
class ReminderDetailFragment: Fragment() {
    private var _binding: FragmentReminderDetailBinding? = null
    private val args: ReminderDetailFragmentArgs by navArgs()
    private val reminderDetailViewModel: ReminderDetailViewModel by viewModels {
        ReminderDetailViewModelFactory(args.reminderId)
    }
    private val binding
        get() = checkNotNull(_binding) {
            "Cannon access binding because it is null.  Is the view visible?"
        }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReminderDetailBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            reminderTitle.doOnTextChanged { text, _, _, _ ->
                reminderDetailViewModel.updateReminder { oldReminder ->
                    oldReminder.copy(title = text.toString())
                }
            }
            reminderLocation.doOnTextChanged { text, _, _, _ ->
                reminderDetailViewModel.updateReminder { oldReminder ->
                    oldReminder.copy(location = text.toString())
                }
            }
            reminderNotes.doOnTextChanged { text, _, _, _ ->
                reminderDetailViewModel.updateReminder { oldReminder ->
                    oldReminder.copy(notes = text.toString())
                }
            }
            reminderDueDate.doOnTextChanged { text, _, _, _ ->
                reminderDetailViewModel.updateReminder { oldReminder ->
                    oldReminder.copy(dueDate = text.toString())
                }
            }
            reminderCompleted.setOnCheckedChangeListener { _, isChecked ->
                reminderDetailViewModel.updateReminder { oldReminder ->
                    oldReminder.copy(completed = isChecked)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reminderDetailViewModel.reminder.collect { reminder ->
                    reminder?.let { updateUi(it) }
                }
            }
        }
    }
    private fun updateUi(reminder: Reminder) {
        binding.apply {
            if (reminderTitle.text.toString() != reminder.title) {
                reminderTitle.setText(reminder.title)
            }
            reminderCompleted.isChecked = reminder.completed
            if (reminderLocation.text.toString() != reminder.location) {
                reminderLocation.setText(reminder.location)
            }
            if (reminderDueDate.text.toString() != reminder.dueDate) {
                reminderDueDate.setText(reminder.dueDate)
            }
            if (reminderNotes.text.toString() != reminder.notes) {
                reminderNotes.setText(reminder.notes)
            }
        }
    }
}
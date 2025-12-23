package com.cacutler.reminders
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
class ReminderDetailViewModel(reminderId: UUID): ViewModel() {
    private val reminderRepository = ReminderRepository.get()
    private val _reminder: MutableStateFlow<Reminder?> = MutableStateFlow(null)
    val reminder: StateFlow<Reminder?> = _reminder.asStateFlow()
    init {
        viewModelScope.launch {
            _reminder.value = reminderRepository.getReminder(reminderId)
        }
    }
    fun updateReminder(onUpdate: (Reminder) -> Reminder) {
        _reminder.update {oldReminder ->
            oldReminder?.let {onUpdate(it)}
        }
    }
    override fun onCleared() {
        super.onCleared()
        reminder.value?.let {reminderRepository.updateReminder(it)}
    }
}
class ReminderDetailViewModelFactory(private val reminderId: UUID): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReminderDetailViewModel(reminderId) as T
    }
}
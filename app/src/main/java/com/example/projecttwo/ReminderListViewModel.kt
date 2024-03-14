package com.example.projecttwo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class ReminderListViewModel: ViewModel() {
    private val reminderRepository = ReminderRepository.get()
    private val _reminders: MutableStateFlow<List<Reminder>> = MutableStateFlow(emptyList())
    val reminders: StateFlow<List<Reminder>>
        get() = _reminders.asStateFlow()
    init {
        viewModelScope.launch {
            reminderRepository.getReminders().collect {
                _reminders.value = it
            }
        }
    }
}
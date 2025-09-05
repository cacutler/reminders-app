package com.example.projecttwo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttwo.databinding.FragmentReminderListBinding
import kotlinx.coroutines.launch
import java.util.UUID
class ReminderListFragment: Fragment() {
    private var _binding: FragmentReminderListBinding? = null
    private val binding get() = checkNotNull(_binding) {
        "Cannot access binding because it is null.  Is the view visible?"
    }
    private val reminderListViewModel: ReminderListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReminderListBinding.inflate(inflater, container, false)
        binding.reminderRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            reminderListViewModel.reminders.collect {reminders ->
                binding.reminderRecyclerView.adapter = ReminderListAdapter(reminders) {reminderId ->
                    findNavController().navigate(ReminderListFragmentDirections.showReminderDetail(reminderId))
                }
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_reminder_list, menu)
    }
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_reminder -> {
                showNewReminder()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun showNewReminder() {
        viewLifecycleOwner.lifecycleScope.launch {
            val newReminder = Reminder(id = UUID.randomUUID(), title = "", dueDate = "", notes = "", location = "", completed = false)
            reminderListViewModel.addReminder(newReminder)
            findNavController().navigate(ReminderListFragmentDirections.showReminderDetail(newReminder.id))
        }
    }
}
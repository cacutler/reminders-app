package com.example.projecttwo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttwo.databinding.FragmentReminderListBinding
import kotlinx.coroutines.Job
private const val TAG = "CrimeListFragment"
class ReminderListFragment: Fragment() {
    private var _binding: FragmentReminderListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null.  Is the view visible?"
        }
    private val reminderListViewModel: ReminderListViewModel by viewModels()
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total reminders: ${reminderListViewModel.reminders.size}")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReminderListBinding.inflate(inflater, container, false)
        binding.reminderRecyclerView.layoutManager = LinearLayoutManager(context)
        val reminders = reminderListViewModel.reminders
        val adapter = ReminderListAdapter(reminders)
        binding.reminderRecyclerView.adapter = adapter
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
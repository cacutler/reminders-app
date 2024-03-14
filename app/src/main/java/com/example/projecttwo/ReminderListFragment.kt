package com.example.projecttwo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projecttwo.databinding.FragmentReminderListBinding
import kotlinx.coroutines.launch
class ReminderListFragment: Fragment() {
    private var _binding: FragmentReminderListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null.  Is the view visible?"
        }
    private val reminderListViewModel: ReminderListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            reminderListViewModel.reminders.collect { reminders ->
                binding.reminderRecyclerView.adapter = ReminderListAdapter(reminders) { reminderId ->
                    findNavController().navigate(ReminderListFragmentDirections.showReminderDetail(reminderId))
                }
            }
        }
    }
}
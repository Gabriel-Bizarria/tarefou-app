package com.taskplanner.tarefou


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.taskplanner.tarefou.adapter.TaskItemClickListener
import com.taskplanner.tarefou.adapter.TaskListAdapter
import com.taskplanner.tarefou.data.model.Task
import com.taskplanner.tarefou.databinding.ActivityMainBinding
import com.taskplanner.tarefou.view.AddTaskActivity
import com.taskplanner.tarefou.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity(), TaskItemClickListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application))
            .get(MainViewModel::class.java)

        insertListeners()
        initRecyclerView()

        lifecycleScope.launch{
            mainViewModel.myQueryResponse.collect { response ->
                adapter.setDataSet(response)
            }
        }
    }

    private fun insertListeners() {
        val adapter = TaskListAdapter(this, mainViewModel)
        binding.btAdd.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        binding.rvTasks.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvTasks.adapter = adapter
    }

    override fun onTaskClicked(task: Task) {
        mainViewModel.task = task
    }
}
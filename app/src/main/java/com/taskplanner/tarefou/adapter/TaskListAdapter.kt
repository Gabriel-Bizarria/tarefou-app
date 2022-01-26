package com.taskplanner.tarefou.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taskplanner.tarefou.databinding.ItemTaskBinding
import com.taskplanner.tarefou.data.model.Task
import com.taskplanner.tarefou.viewmodel.MainViewModel

class TaskListAdapter(
    private val taskItemClickListener: TaskItemClickListener,
    private val mainViewModel: MainViewModel
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var taskList : List<Task> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TaskViewHolder ->{
                val task = taskList[position]
                holder.bind(task)

                holder.binding.ivEdit.setOnClickListener {
                    taskItemClickListener.onTaskClicked(task)
                }

                holder.binding.ivDelete.setOnClickListener {
                        mainViewModel.deleteTask(task)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setDataSet(tasks: List<Task>){
        this.taskList = tasks
        notifyDataSetChanged()
    }

    class TaskViewHolder (  val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(task: Task){
            binding.tvTitleTask.text = task.title
            binding.tvDescription.text = task.description
            binding.tvDate.text = task.date
            binding.tvHour.text = task.hour
        }
    }
}
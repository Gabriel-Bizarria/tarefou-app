package com.taskplanner.tarefou.adapter

import com.taskplanner.tarefou.data.model.Task

interface TaskItemClickListener {
    fun onTaskClicked(task: Task)
}
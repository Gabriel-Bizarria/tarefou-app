package com.taskplanner.tarefou.repository

import com.taskplanner.tarefou.data.dao.TaskDao
import com.taskplanner.tarefou.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao){

   fun getAllTasks(): Flow<List<Task>>{
        return taskDao.getAllTasks()
    }

    fun getTaskById(id: Int): Flow<Task?>{
        return taskDao.queryById(id)
    }

    suspend fun insertTask(task: Task){
        taskDao.save(task)
    }

    suspend fun updateTask(task: Task){
        return taskDao.updateTasks(task)
    }

    suspend fun deleteTask(task: Task){
        return taskDao.deleteTask(task)
    }
}
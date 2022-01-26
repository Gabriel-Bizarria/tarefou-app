package com.taskplanner.tarefou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.taskplanner.tarefou.data.database.AppDataBase
import com.taskplanner.tarefou.data.model.Task
import com.taskplanner.tarefou.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object{
        var taskSelected : Task? = null
    }
    private val repository: TaskRepository
    lateinit var myQueryResponse : Flow<List<Task>>

    init{
        val db = AppDataBase.getDatabase(application).taskDao()
        repository = TaskRepository(db)

        viewModelScope.launch {
            myQueryResponse = repository.getAllTasks()
        }
    }

    fun addTask(task: Task){
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun searchTask(word: String){
        viewModelScope.launch {
            repository.getTaskByTitle(word)
        }
    }
}
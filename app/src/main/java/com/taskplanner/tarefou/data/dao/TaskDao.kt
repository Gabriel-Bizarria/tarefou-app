package com.taskplanner.tarefou.data.dao

import androidx.room.*
import com.taskplanner.tarefou.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(task: Task)

    @Query("SELECT * FROM Task WHERE ID = :id")
    fun queryById(id: Int): Flow<Task?>

    @Query("SELECT * FROM Task ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Task>>

    @Update
    fun updateTasks(task: Task)

    @Delete
    fun deleteTask(task: Task)

}
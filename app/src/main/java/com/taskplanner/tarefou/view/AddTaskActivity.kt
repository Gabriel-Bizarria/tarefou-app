package com.taskplanner.tarefou.view

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.taskplanner.tarefou.databinding.ActivityAddtaskBinding
import com.taskplanner.tarefou.extensions.format
import com.taskplanner.tarefou.extensions.text
import com.taskplanner.tarefou.data.model.Task
import com.taskplanner.tarefou.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddtaskBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddtaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application))
            .get(MainViewModel::class.java)

            insertListeners()
    }

    private fun insertListeners() {
        binding.tiDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tiDate.text = Date(it + offset).format()
            }

            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tiHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener {
               when(timePicker.hour){
                   in 0L..9L -> binding.tiHour.text = "0${timePicker.hour}"
                   else -> binding.tiHour.text = "${timePicker.hour}"
               }

               when(timePicker.minute){
                   in 0L..9L -> binding.tiHour.text += ":0${timePicker.minute}"
                   else -> binding.tiHour.text += ":${timePicker.minute}"
               }
            }
            timePicker.show(supportFragmentManager, "TIME_PICKER_TAG")
        }

        binding.btCreate.setOnClickListener {
            addTask()
            Toast.makeText(applicationContext, "Tarefa criada com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }


        binding.btCancel.setOnClickListener {
            finish()
        }
    }


    private fun addTask(){
        lifecycleScope.launch {
            val name = binding.tiTitle.text
            val description = binding.tiDescription.text
            val date = binding.tiDate.text
            val hour = binding.tiHour.text
            val task = Task(0, name, description, date, hour)
            mainViewModel.addTask(task)
        }
    }


}
package com.taskplanner.tarefou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taskplanner.tarefou.databinding.ActivitySplashScreenBinding
import com.taskplanner.tarefou.view.MainActivity
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivTarefou.alpha = 0f
        binding.ivTarefou.animate().setDuration(2000).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            Thread.sleep(2000)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
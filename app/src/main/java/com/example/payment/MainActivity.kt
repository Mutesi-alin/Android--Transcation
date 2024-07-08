package com.example.payment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.payment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        val transaction= listOf(
            Transaction("Salary", 40000, "1 July 2024", true),
            Transaction("Rent", 16000, "2 July 2024", false),
            Transaction("Dividends", 2400, "4 July 2024", true),
            Transaction("Electricity", 800, "5 July 2024", false),
            Transaction("Internet", 2500, "5 July 2024", false),
            Transaction("Shopping", 3500, "5 July 2024", false),
            Transaction("Bus fare", 500, "11 July 2024", false)
        )
        findViewById<RecyclerView>(R.id.transactionRecyclerView).adapter=

    }
}















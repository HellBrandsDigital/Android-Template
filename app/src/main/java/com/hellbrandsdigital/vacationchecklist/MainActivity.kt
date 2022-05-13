package com.hellbrandsdigital.vacationchecklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hellbrandsdigital.vacationchecklist.databinding.ActivityMainBinding

/**
 * Starting Point for the App
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val number = Util().multiply(2F, 5F)
        binding.HelloWorldText.text = number.toString()
    }
}
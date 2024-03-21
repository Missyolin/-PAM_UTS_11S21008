package com.ifs21008.dinopedia

import android.R
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ifs21008.dinopedia.databinding.ActivityDinosaurDetailBinding

class DinosaurDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinosaurDetailBinding
    private var dino: Dinosaurus? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinosaurDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dino = if (Build.VERSION.SDK_INT >=33) {
            intent.getParcelableExtra(EXTRA_DINOSAURUS, Dinosaurus::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINOSAURUS)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Item ${dino!!.dino_name}"
            loadData(dino!!)
        } else{
            finish()
        }
    }

    private fun loadData(dino: Dinosaurus) {
        binding.ivDinoSpec.setImageResource(dino.dino_icon)
        binding.tvDinoName.text = dino.dino_name
        binding.tvDinoDesc.text = dino.dino_desc
        binding.tvDinoLife.text = dino.dino_period_time
        binding.tvDinoChar.text = dino.dino_physics_characteristic
        binding.tvdinoHab.text = dino.dino_habit
        binding.tvDinoFood.text = dino.dino_food
        binding.tvDinoLeng.text = dino.dino_length
        binding.tvDinoHei.text = dino.dino_height
        binding.tvDinoWe.text = dino.dino_weight
        binding.tvDinoWeak.text = dino.dino_weakness
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_FAMILY = "extra_family"
        const val EXTRA_DINOSAURUS = "extra_dinosaurus"
    }


}
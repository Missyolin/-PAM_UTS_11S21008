package com.ifs21008.dinopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ifs21008.dinopedia.databinding.ActivityDinosaurDetaiBinding

class DinosaurDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinosaurDetaiBinding
    private var family: Family? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinosaurDetaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        family = intent.getParcelableExtra(EXTRA_ITEM)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (family != null) {
            supportActionBar?.title = "Family ${family!!.family_name}"
            loadData(family!!)
        } else {
            finish()
        }
    }

    private fun loadData(family: Family) {
        binding.ivDetailFamily.setImageResource(family.family_icon)
        binding.tvFamilyName.text = family.family_name
        binding.tvFamilyDesc.text = family.family_description
        binding.tvPeriodFamily.text = family.family_life_time_period
        binding.tvFamilyChar.text = family.family_physics_characteristic
        binding.tvHabitFamily.text = family.family_habit
        binding.tvFamilyEnv.text = family.family_environment
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }
}

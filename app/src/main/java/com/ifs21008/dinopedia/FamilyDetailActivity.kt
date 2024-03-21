package com.ifs21008.dinopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.ifs21008.dinopedia.databinding.ActivityFamilyDetailBinding

class FamilyDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFamilyDetailBinding
    private var family: Family? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamilyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        family = intent.getParcelableExtra(EXTRA_FAMILY)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (family != null) {
            supportActionBar?.title = "Family ${family!!.family_name}"
            loadData(family!!)
        } else {
            finish()
        }

        val btnPreview = findViewById<Button>(R.id.btnPrev)

        btnPreview.setOnClickListener{
            val intent = Intent(this@FamilyDetailActivity, DinosaurActivity::class.java)
            intent.putExtra(EXTRA_FAMILY, family) // Mengirim objek Family ke DinosaurActivity
            startActivity(intent)
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
        const val EXTRA_FAMILY = "extra_family"
    }
}

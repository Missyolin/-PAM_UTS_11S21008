package com.ifs21008.dinopedia
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Family (
    var family_icon: Int,
    var family_name: String,
    var family_description: String,
    var family_life_time_period: String,
    var family_physics_characteristic: String,
    var family_habit: String,
    var family_environment: String,
    var family_start: Int,
    var family_end: Int,
) : Parcelable
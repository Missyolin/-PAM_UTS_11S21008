package com.ifs21008.dinopedia

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Dinosaurus (
    var dino_icon: Int,
    var dino_name: String,
    var dino_desc: String,
    var dino_period_time: String,
    var dino_physics_characteristic: String,
    var dino_habit: String,
    var dino_food: String,
    var dino_length: String,
    var dino_height: String,
    var dino_weight: String,
    var dino_weakness: String,
) : Parcelable
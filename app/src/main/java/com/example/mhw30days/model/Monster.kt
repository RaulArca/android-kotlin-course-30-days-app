package com.example.mhw30days.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Monster(
    @StringRes val nameRes: Int,
    @DrawableRes var imageres:Int,
    @StringRes var descriptionRes:Int
    )

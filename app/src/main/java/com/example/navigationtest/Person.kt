package com.example.navigationtest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String?,
    val age: Int?,
    val id: Int?
): Parcelable

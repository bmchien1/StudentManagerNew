package com.example.studentmanagernew

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val name: String,
    val id: String
) : Parcelable {
    override fun toString(): String = "$name ($id)"
}
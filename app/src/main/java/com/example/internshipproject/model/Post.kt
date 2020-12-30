package com.example.internshipproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class Post(var body: String, var id: Int, var title: String, var userId: Int):Parcelable
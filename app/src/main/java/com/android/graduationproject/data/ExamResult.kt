package com.android.graduationproject.data

import com.google.gson.annotations.SerializedName

data class Student(

    @field:SerializedName("student_index")
    val studentIndex: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("Exams_Results")
    val examsResults: List<ExamsResult>
)

data class ExamsResult(

    @field:SerializedName("requested")
    val requested: Boolean,

    @field:SerializedName("Mark")
    val mark: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("Course_name")
    val courseName: String,

    @field:SerializedName("Course_code")
    val courseCode: String,

    @field:SerializedName("Semester")
    val semester: String,

    @field:SerializedName("Credit_hours")
    val creditHours: String

)

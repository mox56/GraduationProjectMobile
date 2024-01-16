package com.android.graduationproject.data

import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes

data class ExamResponse(



	@field:SerializedName("student_index")
	val studentIndex: Int? = null,

	@field:SerializedName("Semester")
	val semester: String? = null,

	@field:SerializedName("Mark")
	val mark: String? = null,

	@field:SerializedName("department")
	val department: Int? = null,

	@field:SerializedName("Course_code")
	val courseCode: String? = null
)

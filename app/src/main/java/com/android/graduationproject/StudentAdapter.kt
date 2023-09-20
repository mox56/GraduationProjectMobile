package com.android.graduationproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val studentModel: MutableList<StudentModel>) :
    RecyclerView.Adapter<StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentModel.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        return holder.bindView(studentModel[position])
    }
}

class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val tvBody: TextView = itemView.findViewById(R.id.tvBody)
    private val tvDept: TextView = itemView.findViewById(R.id.tvDept)
    private val tvCGPA: TextView = itemView.findViewById(R.id.tvCGPA)
    private val tvGPA: TextView = itemView.findViewById(R.id.tvGPA)
    fun bindView(studentModel: StudentModel) {
        tvTitle.text = studentModel.name
        tvBody.text = studentModel.student_index.toString()
        tvDept.text = studentModel.department.toString()
        tvCGPA.text = studentModel.CGPA.toString()
        tvGPA.text = studentModel.GPA.toString()
    }
}
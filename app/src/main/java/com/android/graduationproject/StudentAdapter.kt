package com.android.graduationproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.graduationproject.data.ExamResult

class StudentAdapter(
    var exams:List<ExamResult>
): RecyclerView.Adapter<StudentAdapter.ExamViewHolder>() {

    inner class ExamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_examresult, parent,false)
        return ExamViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        val tvcourse = holder.itemView.findViewById<TextView>(R.id.tvCourse)
        val tvresult = holder.itemView.findViewById<TextView>(R.id.tvResult)
        val cbrequest = holder.itemView.findViewById<CheckBox>(R.id.cbrequest)
        holder.itemView.apply {
            tvcourse.text = exams[position].Course_code
            tvresult.text = exams[position].Mark
            cbrequest.isChecked = exams[position].isChecked

        }
    }

    override fun getItemCount(): Int {
        return exams.size
    }
}


   /* private val differCallback = object : DiffUtil.ItemCallback<ExamResult>() {
        override fun areItemsTheSame(oldItem: ExamResult, newItem: ExamResult): Boolean {
            return oldItem.student_index == newItem.student_index
        }

        override fun areContentsTheSame(oldItem: ExamResult, newItem: ExamResult): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        return ExamViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_exam_results,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        var tvCourse =R.id.course1.toString()
        var tvMark = R.id.mark1.toString()
        var tvSemester = R.id.semester1.toString()
        val exam = differ.currentList[position]
        holder.itemView.apply {
            tvMark = exam.Mark.toString()
            tvCourse= exam.Course_code.toString()
            tvSemester =exam.Semester.toString()

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}


// override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
// val view =
// LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
// return StudentViewHolder(view)
// }
//
// override fun getItemCount(): Int {
// return studentModel.size
// }
//
// override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
// return holder.bindView(studentModel[position])
// }
// }
//
// class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
// private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
// private val tvBody: TextView = itemView.findViewById(R.id.tvBody)
// private val tvDept: TextView = itemView.findViewById(R.id.tvDept)
// private val tvCGPA: TextView = itemView.findViewById(R.id.tvCGPA)
// private val tvGPA: TextView = itemView.findViewById(R.id.tvGPA)
// fun bindView(studentModel: StudentModel) {
// tvTitle.text = studentModel.name
// tvBody.text = studentModel.student_index.toString()
// tvDept.text = studentModel.department.toString()
// tvCGPA.text = studentModel.CGPA.toString()
// tvGPA.text = studentModel.GPA.toString()
// }
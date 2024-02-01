package com.android.graduationproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.graduationproject.data.ExamsResult
import com.android.graduationproject.data.ExamsResultsItem
import com.android.graduationproject.data.Student
import com.android.graduationproject.data.StudentIndexItem
import com.android.graduationproject.data.StudentInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyAdapter(private  val examList: List<ExamsResult>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val coursetv:TextView = itemView.findViewById(R.id.tvCourse)
        val resulttv:TextView = itemView.findViewById(R.id.tvResult)
        val nametv:TextView= itemView.findViewById(R.id.tvname)
        /*val requestbtn:Button = itemView.findViewById(R.id.btnreq)*/
        val semestertv:TextView = itemView.findViewById(R.id.tvSemester)
        val credithourstv: TextView = itemView.findViewById(R.id.tvCredithours)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_examresult, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return examList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = examList[position]
        holder.coursetv.text = currentItem.courseCode
        holder.resulttv.text = currentItem.mark
        holder.nametv.text =currentItem.courseName
        holder.semestertv.text = currentItem.semester
        holder.credithourstv.text = currentItem.creditHours


        }



}




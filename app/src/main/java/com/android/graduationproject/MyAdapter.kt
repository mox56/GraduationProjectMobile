package com.android.graduationproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.graduationproject.data.ExamsResult
import com.android.graduationproject.data.ExamsResultsItem
import com.android.graduationproject.data.Student
import com.android.graduationproject.data.StudentIndexItem
import com.android.graduationproject.data.StudentInt
import com.android.graduationproject.utils.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MyAdapter(private  val examList: List<ExamsResult>, private val onButtonClickListener: OnButtonClickListener): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    fun sendPutRequest(position: Int) {
        val currentItem = examList[position]
        val updatedResult = ExamsResult(
            requested = true,
            mark = currentItem.mark,
            id = currentItem.id,
            courseName = currentItem.courseName,
            courseCode = currentItem.courseCode,
            semester = currentItem.semester,
            creditHours = currentItem.creditHours
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.updateRequested(currentItem.id, updatedResult)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                       // showToast("Course Requested Successful")
                    } else {
                        // Handle unsuccessful response
                        Log.e("PUT_REQUEST_ERROR", response.code().toString())
                    }
                }
            } catch (e: Exception) {
                Log.e("PUT_REQUEST_ERROR", e.message.toString())
            }
        }
    }
  //  private fun showToast(message: String) {
    //    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    //}
    interface OnButtonClickListener {
        fun onButtonClick(position: Int)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val coursetv:TextView = itemView.findViewById(R.id.tvCourse)
        val resulttv:TextView = itemView.findViewById(R.id.tvResult)
        val nametv:TextView= itemView.findViewById(R.id.tvname)
        /*val requestbtn:Button = itemView.findViewById(R.id.btnreq)*/
        val semestertv:TextView = itemView.findViewById(R.id.tvSemester)
        val credithourstv: TextView = itemView.findViewById(R.id.tvCredithours)
        val requestbtn: Button = itemView.findViewById(R.id.btnRequest)


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

        holder.requestbtn.setOnClickListener {
            onButtonClickListener.onButtonClick(position)
        }



        }



}




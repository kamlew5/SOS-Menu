package com.example.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.serializers.PatientList
import com.example.menu.serializers.SubPatientList

class PatientAdapter(val patientList: PatientList) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.patient_list_element, parent, false)
        )
    }

    override fun getItemCount() = patientList.data.size

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patients = patientList.data[position]
        holder.itemView.findViewById<TextView>(R.id.patient_list_id).text = patients.pacjent_id.toString()
        holder.itemView.findViewById<TextView>(R.id.patient_list_name).text = patients.pacjent_imie_nazwisko
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.view.context,patients.pacjent_id.toString(),Toast.LENGTH_SHORT).show()

        }
    }
    class PatientViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}

package com.gokulraj.neocare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.database.Patient
import com.gokulraj.neocare.databinding.ActivityPatientItemBinding

/**
 *
 * This adapter class will handle the display of patients in a RecyclerView.
 *
 * */
class PatientAdapter(
    private val patients: List<Patient>,
    private val onClick: (Patient) -> Unit
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = ActivityPatientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patients[position]
        holder.bind(patient, onClick)
    }

    override fun getItemCount(): Int = patients.size

    class PatientViewHolder(private val binding: ActivityPatientItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(patient: Patient, onClick: (Patient) -> Unit) {
            binding.tvPatientName.text = patient.name
            binding.tvPatientCondition.text = patient.condition
            binding.tvPatientLocation.text = patient.location
            itemView.setOnClickListener { onClick(patient) }
        }
    }
}

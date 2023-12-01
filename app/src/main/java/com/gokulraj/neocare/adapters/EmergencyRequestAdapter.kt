package com.gokulraj.neocare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gokulraj.neocare.database.EmergencyRequest
import com.gokulraj.neocare.databinding.ActivityEmtEmergencyrequestItemBinding

/**
 *
 * This adapter class will handle the display of emergency requests in a RecyclerView.
 * Each item will show the patient's name, address, and a brief description of the emergency.
 *
 * */
class EmergencyRequestAdapter(
    private val emergencyRequests: List<EmergencyRequest>,
    private val onClick: (EmergencyRequest) -> Unit
) : RecyclerView.Adapter<EmergencyRequestAdapter.EmergencyRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyRequestViewHolder {
        val binding = ActivityEmtEmergencyrequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmergencyRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmergencyRequestViewHolder, position: Int) {
        val emergencyRequest = emergencyRequests[position]
        holder.bind(emergencyRequest, onClick)
    }

    override fun getItemCount(): Int = emergencyRequests.size

    class EmergencyRequestViewHolder(private val binding: ActivityEmtEmergencyrequestItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(emergencyRequest: EmergencyRequest, onClick: (EmergencyRequest) -> Unit) {
            binding.tvDetailedRequestPatientName.text = emergencyRequest.patientName
            binding.tvDetailedRequestAddress.text = emergencyRequest.location
            binding.tvDetailedRequestDescription.text = emergencyRequest.emergencyDetails

            itemView.setOnClickListener { onClick(emergencyRequest) }
        }
    }
}

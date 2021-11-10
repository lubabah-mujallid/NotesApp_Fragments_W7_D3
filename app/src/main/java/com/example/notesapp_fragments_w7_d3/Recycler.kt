package com.example.notesapp_fragments_w7_d3

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp_fragments_w7_d3.databinding.RecyclerLayoutBinding

class Recycler(val fragment: MainNoteFragment): RecyclerView.Adapter<Recycler.ViewHolder>() {
    private var messages: ArrayList<Note> = ArrayList()
    class ViewHolder(val binding: RecyclerLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var message = messages[position]
        holder.binding.apply {
            tvID.text = message.id.toString()
            tvNote.text = message.text
            edit.setOnClickListener {
                Log.d("TAG RV", "edit button pressed")
                val action = MainNoteFragmentDirections.actionMainNoteFragmentToUpdateNoteFragment(message.id, message.text)
                fragment.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount() = messages.size

    fun update(newMessages: ArrayList<Note>){
        this.messages = newMessages
        notifyDataSetChanged()
    }
}
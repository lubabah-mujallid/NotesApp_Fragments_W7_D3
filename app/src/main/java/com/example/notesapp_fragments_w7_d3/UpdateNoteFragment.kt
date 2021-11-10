package com.example.notesapp_fragments_w7_d3

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp_fragments_w7_d3.databinding.ActivityMainBinding
import com.example.notesapp_fragments_w7_d3.databinding.FragmentUpdateNoteBinding

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    val t = "TAG Main Fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(t, "onCreate")
        super.onCreate(savedInstanceState)
        initializeViewModel()
    } //this is for logic initialization

    val args: UpdateNoteFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(t, "onCreateView")
        initializeBinding()
        val noteID = args.id
        val noteText = args.text
        binding.tvUpdateNote.text = noteText
        binding.updateNoteButton.setOnClickListener {
            viewModel.updateNote(Note(noteID,noteText), binding.etUpdateNote.text.toString())
            binding.etUpdateNote.text.clear()
            binding.etUpdateNote.clearFocus()
            this.findNavController().navigate(R.id.action_updateNoteFragment_to_mainNoteFragment)
        }
        binding.deleteNoteButton.setOnClickListener {
            viewModel.deleteNote(Note(noteID,noteText))
            this.findNavController().navigate(R.id.action_updateNoteFragment_to_mainNoteFragment)
        }
        binding.cancelButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_updateNoteFragment_to_mainNoteFragment)
        }
        return binding.root
    } //thia is for UI and views

    private lateinit var binding: FragmentUpdateNoteBinding
    private fun initializeBinding() {
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater)
        Log.d("TAG MAIN", "Binding INITIALIZED")
    }

    private lateinit var viewModel: ViewModel
    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        //viewModel.getNotes().observe(this, { notes -> adapter.update(notes as ArrayList<Note>) })
        Log.d("TAG MAIN", "VIEW MODEL INITIALIZED")
    }


}
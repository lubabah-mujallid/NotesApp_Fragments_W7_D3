package com.example.notesapp_fragments_w7_d3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp_fragments_w7_d3.databinding.ActivityMainBinding
import com.example.notesapp_fragments_w7_d3.databinding.FragmentMainNoteBinding

class MainNoteFragment : Fragment() {
    val t = "TAG Main Fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(t, "onCreate")
        super.onCreate(savedInstanceState)
        initializeViewModel()
    } //this is for logic initialization

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(t, "onCreateView")
        initializeBinding()
        initializeRecycler()
        binding.button.setOnClickListener { submitButton() }
        return binding.root
    } //thia is for UI and views

    private lateinit var viewModel: ViewModel
    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getNotes().observe(this, { notes -> adapter.update(notes as ArrayList<Note>) })
        Log.d("TAG MAIN", "VIEW MODEL INITIALIZED")
    }

    private lateinit var binding: FragmentMainNoteBinding
    private fun initializeBinding() {
        binding = FragmentMainNoteBinding.inflate(layoutInflater)
        Log.d("TAG MAIN", "Binding INITIALIZED")
    }

    private lateinit var adapter: Recycler
    private fun initializeRecycler() {
        adapter = Recycler(this)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this.context)
        Log.d("TAG MAIN", "RV INITIALIZED")
    }

    fun submitButton() {
        Log.d("TAG MAIN", "Button Pressed")
        val text = binding.etNote.text.toString()
        viewModel.addNote(text)
        binding.etNote.text.clear()
        binding.etNote.clearFocus()
        Toast.makeText(this.context, "data saved!!", Toast.LENGTH_LONG).show()
    }
}


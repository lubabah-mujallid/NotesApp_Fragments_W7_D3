package com.example.notesapp_fragments_w7_d3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val list: LiveData<List<Note>>
    private val repos: NoteRepository

    init {
        val noteDao = NoteDataBase.getDatabase(application).noteDAO()
        repos = NoteRepository(noteDao)
        list = repos.getNotes
    }

    fun getNotes(): LiveData<List<Note>> {
        return list
    }

    fun addNote(text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG VIEW MODEL", "note is: $text")
            repos.addNote(Note(0, text))
        }
        Log.d("TAG VIEW MODEL", "new data added")
    }

    fun updateNote(note: Note, nNote: String) {
        Log.d("TAG VIEW MODEL", "INSIDE UPDATE")
        CoroutineScope(Dispatchers.IO).launch {
            repos.updateNote(Note(note.id, nNote))
        }
    }

    fun deleteNote(note: Note){
        Log.d("TAG VIEW MODEL", "INSIDE delete")
        CoroutineScope(Dispatchers.IO).launch {
            repos.deleteNote(note)
        }
    }

}
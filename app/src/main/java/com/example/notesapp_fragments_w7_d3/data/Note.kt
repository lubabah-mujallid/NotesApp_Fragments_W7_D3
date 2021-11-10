package com.example.notesapp_fragments_w7_d3

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "NOTES")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
)
package com.sharath.simplenote4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sharath.simplenote4.data.AppDatabase
import com.sharath.simplenote4.data.NoteEntity
import com.sharath.simplenote4.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val notesList = database?.noteDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleNotes = SampleDataProvider.getNotes()
                database?.noteDao()?.insertAll(sampleNotes)
            }
        }
    }

    fun deleteNotes(selectedNotes: List<NoteEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.noteDao()?.deleteNotes(selectedNotes)
            }
        }

    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.noteDao()?.deleteAll()
            }
        }
    }

}
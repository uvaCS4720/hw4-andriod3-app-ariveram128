package edu.nd.pmcburne.hello

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.nd.pmcburne.hello.data.AppDatabase
import edu.nd.pmcburne.hello.data.PlacemarkEntity
import edu.nd.pmcburne.hello.data.PlacemarksRepository
import edu.nd.pmcburne.hello.network.PlacemarksApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
    val tags: List<String> = emptyList(),
    val selectedTag: String = "core",
    val placemarks: List<PlacemarkEntity> = emptyList(),
    val isLoading: Boolean = true
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val repository = PlacemarksRepository(database.placemarkDao(), PlacemarksApi.create())

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.syncPlacemarks()
            val tags = repository.getAllUniqueTags()
            val placemarks = repository.getPlacemarksByTag("core")
            _uiState.value = MainUiState(
                tags = tags,
                selectedTag = "core",
                placemarks = placemarks,
                isLoading = false
            )
        }
    }

    fun selectTag(tag: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(selectedTag = tag)
            val placemarks = repository.getPlacemarksByTag(tag)
            _uiState.value = _uiState.value.copy(placemarks = placemarks)
        }
    }
}

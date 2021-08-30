package com.example.retrofitexample

import androidx.lifecycle.*
import com.example.retrofitexample.model.Student
import com.example.retrofitexample.repository.StudentRepository
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(private val repository: StudentRepository)
    : ViewModel() {

    private val _studentResponse = MutableLiveData<List<Student>>()
    val studentResponse: LiveData<List<Student>>
        get() = _studentResponse

    var message: String? = null

    @Inject
    fun refreshData() {
        _studentResponse.value = listOf()
        viewModelScope.launch {
            repository.getStudentList { newValue -> setNewValue(newValue) }
        }
    }

    fun refreshDataById(id: Int) {
        _studentResponse.value = listOf()
        viewModelScope.launch {
            repository.getStudentById(id) { newValue -> setNewValue(newValue)}
        }
    }

    fun setNewValue(newValue: List<Student>?) {
        newValue.let {
            if (it.isNullOrEmpty()) {
                message = "Unknown Id"
                refreshData()
            } else {
                message = null
                _studentResponse.value = it
            }
        }
    }

    class Factory @AssistedInject constructor(private val repository: StudentRepository)
        : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainViewModel::class)
            return MainViewModel(repository) as T
        }

        @AssistedFactory
        interface Factory {

            fun create() : MainViewModel.Factory
        }

    }
}

class MainViewModelFactory(private val repository: StudentRepository) :
        ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repository) as T
        else
            throw IllegalArgumentException("Unknown view model class")
    }

}
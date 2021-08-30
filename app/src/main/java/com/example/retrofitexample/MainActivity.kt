package com.example.retrofitexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.retrofitexample.Screen.ShowProgressBar
import com.example.retrofitexample.Screen.ShowStudent
import com.example.retrofitexample.model.Student
import com.example.retrofitexample.ui.theme.RetrofitExampleTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //dependency injection by dagger2
        appComponent.inject(this)


        setContent {
            RetrofitExampleTheme {
                viewModel.studentResponse.observeAsState().value.let { studentsList ->
                    if (studentsList.isNullOrEmpty()) {
                        ShowProgressBar()
                    } else {
                        ShowStudent(
                            list = studentsList,
                            refreshList = { viewModel.refreshData() },
                            refreshId = { id -> viewModel.refreshDataById(id) }
                        )
                        viewModel.message?.let { message ->
                            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    @Inject
    fun refresh() {
        viewModel.refreshData()
    }
}


package com.example.retrofitexample.Screen

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
import com.example.retrofitexample.model.Student

@ExperimentalComposeUiApi
@Composable
fun ShowStudent(
    list: List<Student>,
    refreshList: () -> Unit,
    refreshId: (Int) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val text  = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column {

        LazyColumn {
            items(list) {
                StudentCard(student = it)
                Spacer(modifier = Modifier.height(10.dp))

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = refreshList) {
            Text(text = "Refresh")
        }
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = text.value,
            onValueChange = { text.value = it},
            label = { Text(text = "Enter id") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            maxLines = 1,
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )
        Button(
            onClick = { refreshId(text.value.text.toInt()) },
            enabled = text.value.text.isNotEmpty()
        ) {
            Text(text = "Search with id")
        }
    }
}

@Composable
fun StudentCard(student: Student) {
    Column {
        Text(text = student.name)
        Row {
            Text(text = student.campus)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = student.faculty)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = student.group)
        }
    }
}

@Composable
fun ShowProgressBar() {
    CircularProgressIndicator(
        color = Color.Blue
    )
}

package com.vompom.blog.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ContentColumn(
    modifier: Modifier = Modifier,
    loading: Boolean,
    errorMessage: String?,
    onErrorOKClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var errorState by remember { mutableStateOf<String?>(null) }

    // Update errorState whenever errorMessage changes
    LaunchedEffect(errorMessage) {
        errorState = errorMessage
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (loading) Arrangement.Center else Arrangement.Top
    ) {
        when {
            loading -> {
                CircularProgressIndicator()
            }

            else -> {
                content()
            }
        }
    }

    // Show Error Alert Dialog
    errorState?.let {
        ErrorAlert(errorMessage = it) {
            onErrorOKClick()
            // Clear the error state when the "OK" button is clicked
            errorState = null
        }
    }
}

@Composable
fun ErrorAlert(errorMessage: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Error") },
        text = { Text(text = errorMessage) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        })
}

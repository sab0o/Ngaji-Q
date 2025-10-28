package com.example.ngajiq.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInputField(searchQuery: String, onSearchChange: (String) -> Unit){
    // 🔍 Search Input Field
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { onSearchChange },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        placeholder = { Text("Cari video pembelajaran...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(24.dp),
        singleLine = true
    )
}
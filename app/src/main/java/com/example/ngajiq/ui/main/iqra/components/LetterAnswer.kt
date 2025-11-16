package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
@Composable
fun LetterAnswer(
    letters: List<String>,
    selectedLetter: String?,
    jawabanBenar: String,
    isChecked: Boolean,
    onSelect: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        letters.forEach { letter ->

            val state =
                if (!isChecked) {
                    if (selectedLetter == letter) LetterState.Selected else LetterState.Normal
                } else {
                    when {
                        letter == jawabanBenar -> LetterState.Correct
                        letter == selectedLetter -> LetterState.Wrong
                        else -> LetterState.Normal
                    }
                }

            LetterBox(
                letter = letter,
                state = state,
                enabled = true,
                onClick = { onSelect(letter) }
            )
        }
    }
}

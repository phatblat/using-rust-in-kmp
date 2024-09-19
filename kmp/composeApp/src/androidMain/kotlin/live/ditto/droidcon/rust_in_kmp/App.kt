package live.ditto.droidcon.rust_in_kmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Number 1") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = num2,
                onValueChange = { num2 = it },
                label = { Text("Number 2") },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val n1 = num1.toIntOrNull() ?: 0
                val n2 = num2.toIntOrNull() ?: 0
                result = (n1 + n2).toString()
            }) {
                Text("Calculate")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Result: $result",
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

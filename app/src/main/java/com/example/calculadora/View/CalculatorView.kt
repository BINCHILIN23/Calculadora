package com.example.calculadora.View
import com.example.calculadora.ViewModel.CalculatorViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


import com.example.calculadora.ui.theme.CalculadoraTheme

class CalculatorView  : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            CalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraBackground(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun HistoryScreen(
    onBack: () -> Unit
) {
    val viewModel: CalculatorViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {


        Button(onClick = { onBack() }) {
            Text("Back")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "Historial",
            fontSize = 28.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.size(16.dp))


        LazyColumn {
            items(viewModel.history.value) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        text = item.expression,
                        fontSize = 18.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = item.result,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
@Composable

fun CalculadoraBackground(modifier: Modifier = Modifier) {

    var showHistory by remember { mutableStateOf(false) }
    val viewModel: CalculatorViewModel = viewModel()


    if (showHistory) {

        HistoryScreen(
            onBack = { showHistory = false }
        )

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(8.dp)
        ) {


            Button(
                onClick = { showHistory = true },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF817474)
                ),
                modifier = Modifier
                    .padding(4.dp) ,


            ) {
                Text("H")

            }

            Spacer(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxWidth()
            )

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = viewModel.expression.value,
                    fontSize = 24.sp,
                    color = Color.Gray
                )

                Text(
                    text = viewModel.result.value,
                    fontSize = 48.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.size(16.dp))


            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                CalculatorButton(background = Color.Gray, text = "DE") {
                    viewModel.deleteLast()
                }
                CalculatorButton(background = Color.Gray, text = "AC") {
                    viewModel.clear()
                }
                CalculatorButton(background = Color.Gray, text = "%") {
                    viewModel.porcentaje()
                }

                CalculatorButton(background = Color(0xFFFF9800), text = "÷") {
                    viewModel.onOperatorClick("÷")
                }

            }

            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                CalculatorButton(background = Color.DarkGray, text = "7") {
                    viewModel.onNumberClick("7")
                }
                CalculatorButton(background = Color.DarkGray, text = "8") {
                    viewModel.onNumberClick("8")
                }
                CalculatorButton(background = Color.DarkGray, text = "9") {
                    viewModel.onNumberClick("9")
                }
                CalculatorButton(background = Color(0xFFFF9800), text = "X") {
                    viewModel.onOperatorClick("X")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                CalculatorButton(background = Color.DarkGray, text = "4") {
                    viewModel.onNumberClick("4")
                }
                CalculatorButton(background = Color.DarkGray, text = "5") {
                    viewModel.onNumberClick("5")
                }
                CalculatorButton(background = Color.DarkGray, text = "6") {
                    viewModel.onNumberClick("6")
                }
                CalculatorButton(background = Color(0xFFFF9800), text = "-") {
                    viewModel.onOperatorClick("-")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                CalculatorButton(background = Color.DarkGray, text = "1") {
                    viewModel.onNumberClick("1")
                }
                CalculatorButton(background = Color.DarkGray, text = "2") {
                    viewModel.onNumberClick("2")
                }
                CalculatorButton(background = Color.DarkGray, text = "3") {
                    viewModel.onNumberClick("3")
                }
                CalculatorButton(background = Color(0xFFFF9800), text = "+") {
                    viewModel.onOperatorClick("+")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                CalculatorButton(background = Color.DarkGray, text = "+/-") {
                    viewModel.changeSign()
                }

                CalculatorButton(background = Color.DarkGray, text = "0") {
                    viewModel.onNumberClick("0")
                }
                CalculatorButton(background = Color.DarkGray, text = ",") {
                    viewModel.onNumberClick(",")
                }
                CalculatorButton(background = Color(0xFFFF9800), text = "=") {
                    viewModel.onEqualClick()
                }
            }
        }
    }
}
@Composable
fun CalculatorButton(background: Color, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = background),
        modifier = Modifier
            .size(88.dp)
            .padding(0.8.dp)
    ) {
        Text(
            text = text,
            fontSize = 26.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculadoraTheme {

        CalculadoraBackground()

    }
}


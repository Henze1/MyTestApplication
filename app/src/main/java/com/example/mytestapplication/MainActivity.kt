package com.example.mytestapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytestapplication.ui.theme.MyTestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestApplicationTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val user = viewModel.userState.value.user
                    if (user == null) {
                        Log.i("MainActivity", "User is null")
                    }
                    val isLoading = viewModel.userState.value.isLoading
                    user?.let {
                        Image(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(128.dp),
                            painter = loadImageFromUrl(url = user.image),
                            contentDescription = user.name
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(text = "Name : ${user.name} | Age : ${user.age}")
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Email : ${user.email}")
                    }
                    Button(
                        modifier = Modifier.align(Alignment.End),
                        onClick = viewModel::getUser
                    ) {
                        Text(text = "Get User")
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    if (isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

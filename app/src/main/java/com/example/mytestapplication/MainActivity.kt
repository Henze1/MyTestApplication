package com.example.mytestapplication

import android.os.Bundle
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytestapplication.data.User
import com.example.mytestapplication.ui.theme.MyTestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTestApplicationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "World",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val user = viewModel.userState.value.user
                    val isLoading = viewModel.userState.value.isLoading
                    user?.let {
                        Image(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(128.dp),
                            painter = loadImageFromUrl(url = it.picture),
                            contentDescription = it.name
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(text = "Name : ${it.name} | Age : ${it.dob}")
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Email : ${it.email}")

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
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = ("Hello, $name!"),
            modifier = modifier
        )
        var user = updateUI()
        if(user.name == "empty"){
            val newUser = User(
                name = "Addison",
                dob = "50",
                email = "addison.roy@example.com",
                picture = "https://randomuser.me/api/portraits/women/17.jpg"
            )

            user = newUser
        }

        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(128.dp),
            painter =
            if(user.picture == "empty"){
                painterResource(id = R.drawable.ic_launcher_background)
            } else loadImageFromUrl(url = user.picture),
            contentDescription = null
        )

        Text(text = "Name : ${user.name} | Age : ${user.dob}")
        Text(text = "Email : ${user.email}")
    }
}

/**
*Connection to database
*/

//var str: String = ""
//var connection: Connection? = null
//
//fun dbConn() {
//    try {
//        DriverManager.registerDriver(com.mysql.cj.jdbc.Driver())
//        val url = "jdbc:mysql://10.0.0.35:3306/warehouse"
//        val username = "hostuser"
//        val password = "PassWordd123456!@#$%"
//        connection = DriverManager.getConnection(url, username, password)
//        Log.i("MainActivity", "Connected to database successfully!")
//
//        val statement = connection?.createStatement()
//        val resultSet = statement?.executeQuery("SELECT * FROM Materials")
//        while (resultSet?.next() == true) {
//            val id = resultSet.getInt("id")
//            val name = resultSet.getString("name")
//            val description = resultSet.getString("description")
//            val quantity = resultSet.getInt("quantity")
//            str += "ID: $id, Name: $name, Description: $description, Quantity: $quantity\n"
//        }
//        resultSet?.close()
//        statement?.close()
//        connection?.close()
//    } catch (ex: SQLException) {
//        Log.e("MainActivity", "Error connecting to database: ${ex.message}")
//    } finally {
//        Log.i("MainActivity", str)
//    }
//}
fun updateUI(): User {
//    val client = OkHttpClient()
//    val request = okhttp3.Request.Builder()
//        .url("https://randomuser.me/api/?inc=name,dob,email,picture")
//        .build()
//
//    lateinit var response: Response
//    try {
//        response = client.newCall(request).execute()
//    } catch (e: Exception) {
//        Log.e("MainActivity", "Error fetching response data: ${e.message}")
//        e.printStackTrace()
//    }
//    lateinit var jsonString: String
//
//    try {
//        jsonString = response.body.string()
//    } catch (e: Exception) {
//        Log.e("MainActivity", "Error fetching jsonString data: ${e.message}")
//        e.printStackTrace()
//    }
//    val gson = Gson()
//    lateinit var personResponse: PersonResponse
//    try {
//        personResponse = gson.fromJson(jsonString, PersonResponse::class.java)
//    } catch (e: Exception) {
//        Log.e("MainActivity", "Error fetching personResponse data: ${e.message}")
//        e.printStackTrace()
//    }
//
//    var person = Person(
//        name = Name(
//            title = "empty",
//            first =  "empty",
//            last = "empty"
//        ),
//        email = "empty",
//        dob = Dob(
//            date = "empty",
//            age = 0
//        ),
//        picture = Picture(
//            large = "empty",
//            medium = "empty",
//            thumbnail = "empty"
//        )
//    )
//    try {
//        person = personResponse.results.first()
//    } catch (e: Exception) {
//        Log.e("MainActivity", "Error fetching data: ${e.message}")
//        e.printStackTrace()
//    }
//    return User(
//        name = person.name.first,
//        dob = person.dob.age.toString(),
//        email = person.email,
//        picture = person.picture.large
//    )

/**
*Same code, without using exceptions
*/

//    val client = OkHttpClient()
//    val request = Request.Builder()
//        .url("https://randomuser.me/api/?inc=name,dob,email,picture")
//        .build()
//
//    val response = client.newCall(request).execute()
//    val jsonString = response.body?.string()
//
//    val gson = Gson()
//    val personResponse = gson.fromJson(jsonString, PersonResponse::class.java)
//
//    val person = personResponse.results.first()
//
//    val user = User(person.name.first, person.email, person.dob.age.toString(), person.picture.large)

    return User(
        name = "empty",
        dob = "empty",
        email = "empty",
        picture = "empty"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTestApplicationTheme {
        Greeting("Android")
    }
}
package com.example.mytestapplication

import android.database.SQLException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

open class DBConnection {
    var connection: Connection? = null

    init {
        Class.forName("com.mysql.jdbc.Driver")
        val url = "jdbc:mysql://10.0.0.35:3306/warehouse"
        val username = "hostuser"
        val password = "PassWordd123456!@#$%"

        try {
            connection = DriverManager.getConnection(url, username, password)
        } catch (e: SQLException) {
            throw RuntimeException("Error connecting to the database: ", e)
        }
    }
}

class MYJDBC: DBConnection() {
    fun string(): String {
        val query = "SELECT * FROM Materials"
        var result = ""

//        createTable()
//
//        addMaterial("Material 1", "Description 1", 10)

        try {
            val statement: Statement = connection?.createStatement() ?: return ""

            val resultSet = statement.executeQuery(query)
            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val name = resultSet.getString("name")
                val description = resultSet.getString("description")
                val quantity = resultSet.getInt("quantity")

                result += "ID: $id, Name: $name, Description: $description, Quantity: $quantity"
                println("ID: $id, \nName: $name, \nDescription: $description, \nQuantity: $quantity")
            }

        } catch (e: SQLException) {
            throw RuntimeException("Error executing the query: ", e)
        }

        return result
    }

    private fun createTable(){
        val query = "CREATE TABLE IF NOT EXISTS Materials (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), description VARCHAR(255), quantity INT)"

        val statement = connection?.createStatement()
        statement?.executeUpdate(query)
        println("Table created successfully")
        statement?.close()
        connection?.close()
    }

    private fun addMaterial(name: String, description: String, quantity: Int)
    {
        val query = "INSERT INTO Materials (name, description, quantity) VALUES ('$name', '$description', $quantity)"
        val statement = connection?.createStatement()
        statement?.executeUpdate(query)
        println("Material added successfully")
        statement?.close()
        connection?.close()
    }
}
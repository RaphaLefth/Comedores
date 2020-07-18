package com.example.comedor.Models

import java.io.Serializable

data class User(
        var id : String = "",
        var email : String ="",
        var lastname : String ="",
        var nombre : String = "",
        var password : String = "",
        var typeUser : String = "") : Serializable

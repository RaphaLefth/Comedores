package com.example.comedor.Models

import java.io.Serializable

data class Servicio(
    val id : String,
    val descripcion : String
) : Serializable{
    override fun toString(): String {
        return descripcion
    }
}

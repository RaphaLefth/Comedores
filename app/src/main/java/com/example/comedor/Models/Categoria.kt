package com.example.comedor.Models

import java.io.Serializable

class Categoria(
    val id : Int,
    val descripcion : String
) : Serializable {
    override fun toString(): String {
        return descripcion
    }
}
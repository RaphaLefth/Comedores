package com.example.comedor.Models

data class Employees(
    val id: String,
    val dni : String,
    val nombre :String,
    val apellido : String,
    val categoria : String,
    val id_empresa :String,
    val estado : Int,
    val fecha_ingreso : String,
    val fecha_cese : String
) {
}
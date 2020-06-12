package com.example.comedor.Models

import java.util.*

data class Employees(
    val id: Int,
    val dni : String,
    val nombre :String,
    val apellido : String,
    val categoria : String,
    val id_empresa :Int,
    val estado : Int
//    val fecha_ingreso : Date,
//    val fecha_cese : Date
) {
}
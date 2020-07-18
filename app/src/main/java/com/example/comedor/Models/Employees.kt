package com.example.comedor.Models

import java.io.Serializable

data class Employees(
    var dni : String?,
    var nombre :String?,
    var apellido : String?,
    var id_categoria : Int?,
    var ruc_empresa :String?,
    var estado : Int?
//    val fecha_ingreso : Date,
//    val fecha_cese : Date
) : Serializable {
}
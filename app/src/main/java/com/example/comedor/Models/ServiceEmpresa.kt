package com.example.comedor.Models

import java.io.Serializable

class ServiceEmpresa(
    val id_comedor_servicio : String,
    val id_empresa : String,
    val id_comedor : String,
    val id_servicio : String,
    val nombre :String,
    val estado : String,
    val descripcion : String,
    val precio: String
) :Serializable{
}
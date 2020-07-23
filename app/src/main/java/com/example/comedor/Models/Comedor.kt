package com.example.comedor.Models

import java.io.Serializable


data class Comedor(var id_comedor : Int,
                   var nombre : String,
                    var administrador: String,
                    var estado : Int,
                    var telefono : String,
                    var email : String
): Serializable
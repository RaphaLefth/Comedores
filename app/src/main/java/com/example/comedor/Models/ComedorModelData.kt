package com.example.comedor.Models
import java.io.Serializable


data class ComedorModelData(var id_comedor : String
                            , var nombre : String,
                            var administrador: String,
                            var estado : String,
                            var telefono : String,
                            var ruc : String
): Serializable
package com.example.comedor.Models
import java.io.Serializable


data class ComedorModelData(var idComedor : String
                            , var nombreComedor : String,
                            var owner: String,
                            var ruc : String
): Serializable {
}
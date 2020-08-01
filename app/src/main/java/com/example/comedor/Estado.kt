package com.example.comedor

import java.io.Serializable

data class Estado (
    val id : Int,
    val descrpcion : String
): Serializable{
    override fun toString(): String {
        return descrpcion
    }
}

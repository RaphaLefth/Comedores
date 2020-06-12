package com.example.comedor.Api

import com.android.volley.RequestQueue

class Api {
    private val URL_BASE: String = "http://localhost/API-PHP/Api.php"
    private val ROOT_URL :String =URL_BASE + "?apicall="
    private val  URL_CREATE_USUARIO : String = ROOT_URL + "createusuario";
    private val  URL_READ_USUARIOS :String = ROOT_URL + "readusuarios";
        //var request : RequestQueue()
}
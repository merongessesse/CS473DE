package com.dmi.cvbuilder.domain

import java.io.Serializable

class User: Serializable{
    private var firstName:String
    private var lastName: String
    private var username: String
    private var password: String

    constructor(firstName: String, lastName: String, username: String, password:String) {
        this.firstName = firstName
        this.lastName = lastName
        this.username = username
        this.password = password
    }


    fun getUserName(): String{
        return username
    }
    fun getpassword(): String{
        return password
    }

    fun getFirstName(): String{
        return firstName
    }
    fun getLastName(): String{
        return lastName
    }
}
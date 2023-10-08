package com.example.googleformwrapper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.example.inheritanceapp

open class Secondary: BaseClass() {
    override fun role() {
//        super.role()
        println("Knight of the House of BaseClass")
    }
}
package com.example.data.mapper

interface Mapper<F,T> {

    fun mapFrom(from:F):T
}
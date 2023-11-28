package com.quadroidev.medic.core.components.maper

fun interface Mapper<F, T> {
    fun map(from: F): T
}

fun <F, T> Mapper<F, T>.map(collection: Collection<F>) = collection.map { map(it) }
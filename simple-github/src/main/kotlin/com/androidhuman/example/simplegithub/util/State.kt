package com.androidhuman.example.simplegithub.util

sealed class State<out T> {
    class Init : State<Nothing>()
    class Loading : State<Nothing>()
    class Success<out T>(val data : T) : State<T>()
    class Error(val message: String) : State<Nothing>()
}
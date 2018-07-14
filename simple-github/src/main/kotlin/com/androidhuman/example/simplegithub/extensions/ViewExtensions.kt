package com.androidhuman.example.simplegithub.extensions

import android.view.View

// VISIBILITY
fun View.visible() { visibility = View.VISIBLE }

fun View.invisible() { visibility = View.INVISIBLE }

fun View.gone() { visibility = View.GONE }

fun View.onClick(func: () -> Unit) = setOnClickListener { func() }
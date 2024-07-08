package com.example.payment
data class Transaction(
    val name: String,
    val amount: Int,
    val date: String,
    val isIncome: Boolean,
)
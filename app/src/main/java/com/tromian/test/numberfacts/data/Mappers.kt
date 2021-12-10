package com.tromian.test.numberfacts.data

import com.tromian.test.numberfacts.data.db.NumberEntity
import com.tromian.test.numberfacts.data.network.NumberResponse
import com.tromian.test.numberfacts.model.Number

fun Number.toEntity(): NumberEntity{
    return NumberEntity(
        number = this.number,
        text = this.text
    )
}
fun NumberEntity.toDomain(): Number{
    return Number(
        number = this.number,
        text = this.text
    )
}

fun NumberResponse.toDomain() : Number{
    val newNumber = this.number
    val newText = this.text
    return Number(
        number = newNumber,
        text = newText
    )
}
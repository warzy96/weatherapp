package com.project.personal.weatherapp.util

private fun generateColor(color: Int, ratio: Float): Int {
    val alpha = color shr 24 and 0xff
    val red = color and 0xff0000 shr 16
    val green = color and 0xff00 shr 8
    val blue = color and 0xff

    val redResult = (red * ratio).toInt()
    val greenResult = (green * ratio).toInt()
    val blueResult = (blue * ratio).toInt()

    return alpha shl 24 or (redResult shl 16) or (greenResult shl 8) or blueResult
}

fun generateColorsInHex(startingColor: Int, ratio: Float, numberOfColors: Int): IntArray {
    val colors = IntArray(numberOfColors)
    for (i in 0 until numberOfColors) {
        colors[i] = generateColor(startingColor, ratio + (1 - ratio) / numberOfColors * (numberOfColors - i))
    }
    return colors
}

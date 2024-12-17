package com.example.mygame

import java.util.*

class Star {

    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    private val generator = Random()

    constructor(width: Int, height: Int) {
        maxX = width
        maxY = height
        minX = 0
        minY = 0

        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)

        speed = generator.nextInt(15) + 1
    }

    fun update(playerSpeed: Int) {
        y += playerSpeed
        y += speed

        // If the star moves off the bottom of the screen, reset it to the top
        if (y > maxY) {
            y = 0
            x = generator.nextInt(maxX)
            speed = generator.nextInt(15) + 1
        }
    }

    var starWidth: Int = 0
        get() {
            return generator.nextInt(10) + 1
        }
}

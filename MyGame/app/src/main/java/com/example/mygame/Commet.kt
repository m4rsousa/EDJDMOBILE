package com.example.mygame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.Random

class Commet {
    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap: Bitmap
    var boosting = false
    var detectCollision: Rect
    val generator = Random()

    constructor(context: Context, width: Int, height: Int) {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.commet)

        maxX = width - bitmap.width
        minX = 0

        maxY = height
        minY = 0

        x = generator.nextInt(maxX) // Random x position across the screen width
        y = minY

        speed = generator.nextInt(6) + 10
        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(playerSpeed: Int) {
        // Move the star downwards by updating the y position
        y += playerSpeed
        y += speed

        // If the star moves off the bottom of the screen, reset it to the top
        if (y > maxY) {
            y = 0  // Reset to the top of the screen
            x = generator.nextInt(maxX)  // Randomize x position for variety
            speed = generator.nextInt(15) + 1  // Randomize speed for variety
        }

        // Update collision rectangle
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }
}

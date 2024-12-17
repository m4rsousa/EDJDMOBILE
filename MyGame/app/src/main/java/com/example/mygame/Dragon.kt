package com.example.mygame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Dragon {

    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var counter = 0

    var bitmap: Bitmap
        get() {
            counter++
            if (counter > 40) counter = 0
            return getBitMapFrame(counter / 10)
        }

    var boosting = false
    private val GRAVITY = -20
    private val MAX_SPEED = 30
    private val MIN_SPEED = 1

    var spriteBitmap: Bitmap

    var detectCollision: Rect

    constructor(context: Context, width: Int, height: Int) {

        spriteBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.spritesheet)

        bitmap = getBitMapFrame(0)

        minX = 0
        maxX = width

        maxY = 0
        minY = height

        x = 100
        y = height - bitmap.height -50

        speed = 1

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun getBitMapFrame(frame: Int): Bitmap {

        val frameWidth = spriteBitmap.width / 4
        val frameHeight = spriteBitmap.height

        var f = frame
        if (f > 3)
            f = 3

        return Bitmap.createBitmap(spriteBitmap, f * frameWidth, 0, frameWidth, frameHeight)
    }

    fun update() {
        if (boosting) speed += 2
        else speed -= 5
        if (speed > MAX_SPEED) speed = MAX_SPEED
        if (speed < MIN_SPEED) speed = MIN_SPEED

        y -= speed + GRAVITY

        if (y < minY) y = minY
        if (y > maxY) y = maxY

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }
}








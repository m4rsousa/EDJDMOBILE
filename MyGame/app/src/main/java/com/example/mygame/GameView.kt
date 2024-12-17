package com.example.mygame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.SurfaceHolder
import android.view.SurfaceView


class GameView : SurfaceView, Runnable {

    var playing = false
    var gameOver = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var canvas: Canvas

    lateinit var paint: Paint
    var stars = arrayListOf<Star>()
    var coins = arrayListOf<StarCoin>()
    var commets = arrayListOf<Commet>()
    lateinit var player: Dragon

    var starcounter = 0
    var lives =3

    private fun init(context: Context, width: Int, height: Int) {

        surfaceHolder = holder
        paint = Paint()

        for (i in 0..100) {
            stars.add(Star(width, height))
        }

        for (i in 0..1) {
            coins.add(StarCoin(context, width, height))
        }

        for (i in 0..1) {
            commets.add(Commet(context, width, height))
        }

        player = Dragon(context, width, height)

    }

    constructor(context: Context?, width: Int, height: Int) : super(context) {
        init(context!!, width, height)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context!!, 0, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context!!, 0, 0)
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause() {
        playing = false
        gameThread?.join()
    }
    fun die(){
        playing = false
        gameOver = true
    }
    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }


    fun update() {
        if (!gameOver) {
            for (s in stars) {
                s.update(player.speed)
            }
            for (c in coins) {
                c.update(player.speed)
                if (Rect.intersects(player.detectCollision, c.detectCollision)) {
                    starcounter += 1
                    c.y = -300
                }
            }
            for (e in commets) {
                e.update(player.speed)
                if (Rect.intersects(player.detectCollision, e.detectCollision)) {
                    if(lives!=0){
                        lives-=1
                    }
                    else{
                        die()
                        break
                    }
                }
            }
            player.update()
        }
    }

    fun draw() {
        if (surfaceHolder.surface.isValid) {
            canvas = surfaceHolder.lockCanvas()

            canvas.drawColor(Color.BLACK)

            paint.color = Color.WHITE
            paint.textSize = 70f
            paint.isAntiAlias = true
            if (!gameOver) {
                canvas.drawText("Coins: $starcounter", 50f, 100f, paint)
                canvas.drawText("Lives: $lives", -50f, 100f, paint)
                for (star in stars) {
                    paint.strokeWidth = star.starWidth.toFloat()
                    canvas.drawPoint(star.x.toFloat(), star.y.toFloat(), paint)
                }

                canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

                for (c in coins) {
                    canvas.drawBitmap(c.bitmap, c.x.toFloat(), c.y.toFloat(), paint)
                }

                for (e in commets) {
                    canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
                }
            }
            else{
                paint.color = Color.RED
                paint.textSize = 100f
                canvas.drawText("Game Over", width / 150f, height / 100f, paint)
                canvas.drawText("Star Count: $starcounter",width /100f, height /50f, paint)
            }
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control() {
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
            player.boosting = true
            player.x = event.x.toInt()
            }
            ACTION_UP -> {
               player.boosting = false

            }
            MotionEvent.ACTION_MOVE -> {
                player.x = event.x.toInt()
            }
        }
        return true
    }
}


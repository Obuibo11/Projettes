package com.example.projet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Build
import androidx.annotation.RequiresApi

class BalleCanon (var view: CanonView, var canonballRadius : Float, var canonballVitesse: Float, val angle:Double ) {
    var canonball = PointF()
    var canonballVitesseX = 0f
    var canonballVitesseY = 0f
    var canonballOnScreen = true
    var canonballPaint = Paint()


    init {
        canonballPaint.color = Color.RED
        this.launch(angle)
    }

    fun launch(angles: Double) {
        val angle=angles- Math.PI/2
        canonball.x = view.screenWidth/2f
        canonball.y = view.screenHeight -2*canonballRadius
        canonballVitesseX=(canonballVitesse*Math.cos(angle)).toFloat()
        canonballVitesseY=(canonballVitesse*Math.sin(angle)).toFloat()
        canonballOnScreen = true

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun update(interval: Double) {
        if (canonballOnScreen) {
            canonball.x += (interval * canonballVitesseX).toFloat()
            canonball.y += (interval * canonballVitesseY).toFloat()

            /* Vérifions si la balle touche l'obstacle ou pas */
"""            if (canonball.x + canonballRadius > obstacle.obstacle.left &&
                canonball.y + canonballRadius > obstacle.obstacle.top &&
                canonball.y - canonballRadius < obstacle.obstacle.bottom) {
                canonballVitesseX *= -1
                canonball.offset((5*canonballVitesseX*interval).toFloat(), 0f)
                view.reduceTimeLeft()
                view.playObstacleSound()
            }
"""
            // Si elle sorte de l'écran
            if (canonball.x + canonballRadius > view.screenWidth
                || canonball.x - canonballRadius < 0) {
                canonballOnScreen = false
            }
            else if (canonball.y + canonballRadius > view.screenHeight
                || canonball.y - canonballRadius < 0) {
                canonballOnScreen = false
            }
"""            else if (canonball.x+canonballRadius>cible.cible.left
                && canonball.y+canonballRadius>cible.cible.top
                && canonball.y-canonballRadius<cible.cible.bottom) {
                if (canonball.x + canonballRadius > cible.cible.left
                    && canonball.y + canonballRadius > cible.cible.top
                    && canonball.y - canonballRadius < cible.cible.bottom) {
                    cible.detectChoc(this)
                }

            }
"""
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(canonball.x, canonball.y, canonballRadius,
            canonballPaint)
    }
    fun resetCanonBall() {
        canonballOnScreen = true
    }
}
package com.example.projet

import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.PointF

class Canon (var canonBaseRadius: Float, var canonLongueur: Float, hauteur: Float, var largeur: Float, val view: CanonView) {
    val canonPaint = Paint()
    var finCanon = PointF(view.screenWidth/2+largeur,
        view.screenHeight-canonLongueur)

    fun draw(canvas: Canvas) {
        canonPaint.strokeWidth = largeur * 1.5f
        canvas.drawLine(view.screenWidth/2, view.screenHeight, finCanon.x,finCanon.y, canonPaint)
        canvas.drawCircle(view.screenWidth/2, view.screenHeight, canonBaseRadius,
            canonPaint)
    }

    fun setFinCanon(hauteur: Float) {
        finCanon.set(PointF(view.screenWidth/2+hauteur,
            view.screenHeight-canonLongueur))
    }
    fun align( angles: Double) {
        val angle=angles
        finCanon.x = (canonLongueur * Math.sin(angle)+view.screenWidth/2).toFloat()
        finCanon.y = (-canonLongueur * Math.cos(angle)
                + view.screenHeight ).toFloat()
    }
}

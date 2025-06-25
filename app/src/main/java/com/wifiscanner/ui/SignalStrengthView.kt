package com.wifiscanner.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

/**
 * A custom view to visualize WiFi signal strength using bar indicators.
 */
class SignalStrengthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Signal strength levels from 0-5
    private var signalLevel: Int = 0

    // Paint objects for different bar states
    private val activePaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    private val inactivePaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.FILL
    }

    // Customize colors if needed
    private val activeColor = ContextCompat.getColor(context, android.R.color.holo_green_dark)
    private val weakColor = ContextCompat.getColor(context, android.R.color.holo_orange_light)
    private val criticalColor = ContextCompat.getColor(context, android.R.color.holo_red_light)

    /**
     * Set the signal level (0-5)
     * @param level WiFi signal strength level
     */
    fun setSignalLevel(level: Int) {
        signalLevel = level.coerceIn(0, 5)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        val barWidth = width / 6f
        val spacing = barWidth / 4
        
        // Draw 5 bars with increasing height
        for (i in 0 until 5) {
            val left = i * (barWidth + spacing)
            val right = left + barWidth
            
            val paint = if (i < signalLevel) {
                when {
                    signalLevel <= 1 -> inactivePaint.color = criticalColor
                    signalLevel <= 2 -> inactivePaint.color = weakColor
                    else -> inactivePaint.color = activeColor
                }
                activePaint
            } else {
                inactivePaint
            }
            
            // Bar height proportional to index
            val barHeight = height * ((i + 1) / 5f)
            val rect = RectF(left, height - barHeight, right, height.toFloat())
            
            canvas.drawRect(rect, paint)
        }
    }
}
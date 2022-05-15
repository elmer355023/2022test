package s1092796.pu.edu.com.example.a2022test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),
    SurfaceHolder.Callback, GestureDetector.OnGestureListener {
    var surfaceHolder: SurfaceHolder
    var BG: Bitmap
    var BGmoveX:Int = 0
    var fly1:Fly
    var gDetector: GestureDetector

    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        surfaceHolder.addCallback(this)
        fly1 = Fly(context!!)
        gDetector = GestureDetector(context, this)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {


        var canvas: Canvas = surfaceHolder.lockCanvas()
            drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {


    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }

    fun drawSomething(canvas:Canvas) {
        var SrcRect: Rect = Rect(0, 0, BG.width, BG.height)
        var w:Int = width
        var h:Int = height

        BGmoveX --
        var BGnewX:Int = w + BGmoveX

        if (BGnewX <= 0) {
            BGmoveX = 0

            canvas.drawBitmap(BG, BGmoveX.toFloat(), 0f, null)
        } else {

            var DestRect:Rect = Rect(BGmoveX, 0, BGmoveX+w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
            DestRect = Rect(BGnewX, 0, BGnewX+w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
        }

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.textSize = 50f
        canvas.drawText("射擊遊戲(作者 : 黃義淼)", 50f,50f, paint)

        fly1.draw(canvas)
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, p2: Float, p3: Float): Boolean {
        fly1.y = e2!!.y.toInt() - fly1.h/2
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
}
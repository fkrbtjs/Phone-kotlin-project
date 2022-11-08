package aop.part3.phone

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {


    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val with = parent.width
        val heigtht = parent.height
//        val kboDrawable: Drawable? = ResourcesCompat.getDrawable(context.resources,R.drawable.kbo,null)
//        val kboWith = kboDrawable?.intrinsicWidth
//        val kboheight = kboDrawable?.intrinsicHeight
//        val left = with/2 - kboWith?.div(2) as Int
//        val top = heigtht/2 - kboheight?.div(2) as Int
//        canvas.drawBitmap(BitmapFactory.decodeResource(context.resources,R.drawable.kbo),
//            left.toFloat(),top.toFloat(), null)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)


        outRect.set(10,10,10,0)


        view.setBackgroundColor(Color.parseColor("#f3f3f3"))
        ViewCompat.setElevation(view, 20.0f)
    }
}
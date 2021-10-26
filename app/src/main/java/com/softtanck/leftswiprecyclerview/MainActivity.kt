package com.softtanck.leftswiprecyclerview

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    internal class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflate: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_demo, parent, false)
            return ViewHolder(inflate)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {}
        override fun getItemCount(): Int {
            return 2
        }
    }

    internal class CallBack : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            /**
             * call max distance start onSwiped call
             */
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                /**
                 * get [TextView.getWidth]
                 */
                val viewGroup = viewHolder.itemView as ViewGroup
                val textView = viewGroup.getChildAt(1) as TextView
                val layoutParams = textView.layoutParams
                if (abs(dX) <= layoutParams.width) {
                    /**
                     * move [RecyclerView.ViewHolder] distance
                     */
                    viewHolder.itemView.scrollTo((-dX).toInt(), 0)
                    /**
                     * callAction or register click bind view
                     */
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.rl_list)
        val myAdapter = MyAdapter()
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = myAdapter
        val itemTouchHelper = ItemTouchHelper(CallBack())
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
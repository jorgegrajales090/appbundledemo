package com.time.someplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = this.findViewById<Button>(R.id.button1)

        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })
    }
}

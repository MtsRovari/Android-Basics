package com.example.mateusrovari.activityforresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {

    internal var textView1: TextView? = null
    internal var editText2: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun OpenSecond(v: View) {
        val i = Intent(applicationContext, SecondActivity::class.java)
        startActivityForResult(i, 1)
        textView1 = findViewById<View>(R.id.textView1) as TextView
        editText2 = findViewById(R.id.editText2)

        val messagess = textView1!!.text.toString()
        i.putExtra("Data", messagess)

    }

    // Call Back method  to get the Message form other Activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val receivedMessage = data.getStringExtra("Data")
            textView1!!.setText(receivedMessage)
        }

    }

}

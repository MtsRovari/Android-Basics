package com.example.mateusrovari.activityforresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class SecondActivity : Activity() {

    internal var editText1: EditText? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        editText1 = findViewById<View>(R.id.editText1) as EditText
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val receivedMessagess = data!!.getStringExtra("Data")
            editText1!!.setText(receivedMessagess)
        }

    }

    fun SendMeHome(v: View) {
        val i = Intent()
        val message = editText1!!.text.toString()
        i.putExtra("Data", message)
        setResult(Activity.RESULT_OK, i)
        finish()
    }

}


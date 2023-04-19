package com.example.authreg

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edtfirst:EditText
    lateinit var edtsecond:EditText
    lateinit var edtemail:EditText
    lateinit var edtpassword:EditText
    lateinit var btncreate:Button
    lateinit var btnlogin:Button

    lateinit var db:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtfirst = findViewById(R.id.edtfirst)
        edtsecond = findViewById(R.id.edtsecond)
        edtemail = findViewById(R.id.edtemail)
        edtpassword = findViewById(R.id.edtpassword)
        btncreate= findViewById(R.id.btncreate)
        btnlogin = findViewById(R.id.btnlogin)


        //CREATING OUR DATABASE
        db= openOrCreateDatabase("Kashydb", Context.MODE_PRIVATE, null)
        //creating our tables
        db.execSQL("CREATE TABLE IF NOT EXISTS users(firstname VARCHAR, secondname VARCHAR, email VARCHAR , password VARCHAR)")

        btncreate.setOnClickListener {
            var firstname_edt = edtfirst.text.toString().trim()
            var secondname_edt = edtsecond.text.toString().trim()
            var email_edt = edtemail.text.toString().trim()
            var password_edt = edtpassword.text.toString().trim()


            //validate your text
            if (firstname_edt.isEmpty()||secondname_edt.isEmpty()|| email_edt.isEmpty()|| password_edt.isEmpty()){
                Toast.makeText(this,"Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            }else{
                //insert data
                db.execSQL("INSERT INTO users VALUES('\"+firstname_edt+\"', '\"+secondname_edt+\"', '\"+email_edt+\"', '\"+password_edt+\"')")
                
                Toast.makeText(this, "User Created Succesfully", Toast.LENGTH_SHORT).show()

                //navigation to user from one activity to another
                var gotologin = Intent(this,LoginActivity::class.java)
                startActivity(gotologin)
            }


        }

    }
}



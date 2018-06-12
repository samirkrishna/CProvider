package com.hasini.samirkrishna.cprovider

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    var lview:ListView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status=ContextCompat.checkSelfPermission(this, arrayOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.READ_CALL_LOG).toString())

        if(status==PackageManager.PERMISSION_GRANTED)
        {
            contacts()
        }
        else
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.READ_CALL_LOG),123)
    }

    fun contacts()//to display name and number
    {
        lview=findViewById(R.id.lview)

        var reslover=contentResolver
        var cursor= reslover.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        var from= arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER)

        var to= intArrayOf(R.id.tv1,R.id.tv2)
        var adapter= SimpleCursorAdapter(this,R.layout.indiview,cursor,from,to,0)

        lview?.adapter=adapter

    }
   /* fun log()//for call log
    {
        lview=findViewById(R.id.lview)

        var reslover=contentResolver
        var cursor= reslover.query(CallLog.Calls.CONTENT_URI,null,null,null,null)

        var from= arrayOf(CallLog.Calls.CACHED_NAME,CallLog.Calls.NUMBER)

        var to= intArrayOf(R.id.tv1,R.id.tv2)
        var adapter= SimpleCursorAdapter(this,R.layout.indiview,cursor,from,to,0)

        lview?.adapter=adapter
    }*/

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            contacts()
        if(grantResults[1]==PackageManager.PERMISSION_GRANTED)
            contacts()
        else
            Toast.makeText(this,"Provide permission",Toast.LENGTH_LONG).show()
    }
}

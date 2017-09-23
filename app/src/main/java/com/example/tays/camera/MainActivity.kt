package com.example.tays.camera

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var photoImageView: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val photoButton = findViewById<Button>(R.id.photoBtn) //IDENTIFY THAT IT IS BUTTON TYPE
        photoImageView = findViewById(R.id.photoimageView) //we are already set the type of photoImageView
        if (!hasCamera()){
            photoButton.setEnabled(false) //check that camera button exist or not.
        }
    }
    private fun hasCamera():Boolean{
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }
    fun launchCamera(view: View){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //press button will come to this ต้นทาง
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE) //open camera mode
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //ขากลับ
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val extra = data!!.extras //รับภาพเก็บเข้ามาใน extra
            val photo = extra.get("data") as Bitmap // เก็บไว้ในphoto ในลักษณะบิทแมพ
            photoImageView.setImageBitmap(photo) //วางไว้ในimageView
        }
    }
}

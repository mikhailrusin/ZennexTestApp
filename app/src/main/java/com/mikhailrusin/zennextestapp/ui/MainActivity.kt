package com.mikhailrusin.zennextestapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.navigation.findNavController
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.news_list.NewsListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), ImageHandleListener {
    private var hideMenu = false
    private var uriToImageFromCamera: Uri? = null

    private val pickImageFromGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { handleImage(it) }
        }

    private val takePictureWithCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                uriToImageFromCamera?.let { handleImage(it) }
            }
        }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getImageFromGallery()
            } else {
                onPermissionRequestFailure()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            toolbar.title = item.title
            when (item.itemId) {
                R.id.newsListFragment -> {
                    hideMenu = false
                    invalidateOptionsMenu()
                    navController.navigate(R.id.newsListFragment)
                    true
                }
                R.id.imageFragment -> {
                    hideMenu = true
                    invalidateOptionsMenu()
                    navController.navigate(R.id.imageFragment)
                    true
                }
                R.id.mapFragment -> {
                    hideMenu = true
                    invalidateOptionsMenu()
                    navController.navigate(R.id.mapFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun isStoragePermissionGranted() =
        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED

    private fun isCameraPermissionGranted() =
        checkSelfPermission(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED


    override fun requestImageFromGallery() {
        if (isStoragePermissionGranted()) {
            getImageFromGallery()
        } else {
            requestStoragePermission()
        }
    }

    override fun requestImageFromCamera() {
        if (isCameraPermissionGranted()) {
            getImageFromCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun requestStoragePermission() {
        requestPermissions.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun requestCameraPermission() {
        requestPermissions.launch(Manifest.permission.CAMERA)
    }

    private fun onPermissionRequestFailure() {
        val message = resources.getString(R.string.grant_failure)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getImageFromGallery() {
        pickImageFromGallery.launch("image/*")
    }

    private fun getImageFromCamera() {
//        val date = SimpleDateFormat.getDateTimeInstance().format(Date())
//        val file = File("$filesDir/$date.jpg")
//        val uri = FileProvider.getUriForFile(this, "file_provider", file)
//        takePictureWithCamera.launch(uri)
//        uriToImageFromCamera = uri
    }

    private fun handleImage(uri: Uri) {
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show()
        // совершить переход на новый фрагмент, передать ему url и загрузить картинку
//        (supportFragmentManager.fragments.last() as EditCollageFragment).uploadImage(uri)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return if (hideMenu) {
            false
        } else {
            menuInflater.inflate(R.menu.options_menu, menu)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                (nav_host_fragment.childFragmentManager.fragments[0] as NewsListFragment)
                    .refreshNews()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (!findNavController(R.id.nav_host_fragment).popBackStack()) {
            super.onBackPressed()
        }
    }
}
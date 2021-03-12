package com.mikhailrusin.zennextestapp.ui.image_pick

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.ImageHandleListener
import kotlinx.android.synthetic.main.fragment_image_pick.*

class ImagePickFragment : Fragment(R.layout.fragment_image_pick) {
    private lateinit var imageHandleListener: ImageHandleListener
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_image_camera.setOnClickListener {
            add_image.collapse()
            // pick image with camera
            imageHandleListener.requestImageFromCamera()
        }
        add_image_gallery.setOnClickListener {
            add_image.collapse()
            // pick image from gallery
            imageHandleListener.requestImageFromGallery()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        imageHandleListener = context as ImageHandleListener
    }
}
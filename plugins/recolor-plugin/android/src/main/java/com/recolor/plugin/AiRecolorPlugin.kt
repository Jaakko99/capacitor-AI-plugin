
// A helper function to convert the file URI to a Bitmap
private fun getBitmapFromUri(context: Context, imagePath: String): Bitmap? {
    try {
        val uri = Uri.parse(imagePath)
        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        return bitmap
    } catch (e: Exception) {
        Log.e("AiRecolor", "Error loading bitmap from URI: $imagePath", e)
        return null
    }
}

// 1. Configure the Subject Segmenter
val segmenterOptions = SubjectSegmenterOptions.Builder()
    .setOptions(SubjectSegmenterOptions.SINGLE_SUBJECT_MASK)
    .build()

val segmenter = SubjectSegmentation.getClient(segmenterOptions)
val image = InputImage.fromBitmap(bitmap, 0) // ML Kit uses InputImage

// 2. Process the image
segmenter.process(image)
    .addOnSuccessListener { result ->
        // Success: result contains the segmentation masks.
        val mask = result.getSubjectMasks()[0] // Get the first (and only) subject mask
        
        // --- NEXT STEP: Recolor the Bitmap using the 'mask' ---
        
        // (Placeholder for Recolor and Save)
        val recoloredBitmap = applyRecolor(bitmap, mask, newColorHex) 
        val resultPath = saveBitmapToFile(recoloredBitmap)
        
        // Resolve the JavaScript call
        val ret = JSObject()
        ret.put("recoloredImagePath", resultPath)
        call.resolve(ret)

    }
    .addOnFailureListener { e ->
        // Failure: Segmentation failed
        call.reject("ML Kit Segmentation failed: " + e.localizedMessage)
    }

    // Function to handle the actual recoloring and blending
private fun applyRecolor(original: Bitmap, mask: SubjectSegmenterResult.SubjectMask, newColorHex: String): Bitmap {
    val output = original.copy(Bitmap.Config.ARGB_8888, true)
    val width = output.width
    val height = output.height
    
    // Convert hex string to Android Color integer
    val newColorInt = Color.parseColor(newColorHex)
    val newR = Color.red(newColorInt)
    val newG = Color.green(newColorInt)
    val newB = Color.blue(newColorInt)

    // The mask contains a FloatBuffer of confidence values
    val confidenceBuffer = mask.confidenceMask.asFloatBuffer()

    for (y in 0 until height) {
        for (x in 0 until width) {
            val confidence = confidenceBuffer.get(y * width + x)
            
            // Only recolor if the confidence is high enough (e.g., > 0.5)
            if (confidence > 0.5f) {
                val originalPixel = output.getPixel(x, y)
                val originalA = Color.alpha(originalPixel)
                val originalR = Color.red(originalPixel)
                val originalG = Color.green(originalPixel)
                val originalB = Color.blue(originalPixel)
                
                // Simple blending for MVP (you can get much more advanced here)
                val blendedR = ((newR * confidence) + (originalR * (1 - confidence))).toInt().coerceIn(0, 255)
                val blendedG = ((newG * confidence) + (originalG * (1 - confidence))).toInt().coerceIn(0, 255)
                val blendedB = ((newB * confidence) + (originalB * (1 - confidence))).toInt().coerceIn(0, 255)
                
                output.setPixel(x, y, Color.argb(originalA, blendedR, blendedG, blendedB))
            }
        }
    }
    return output
}

// Function to save the recolored Bitmap to the device's cache
private fun saveBitmapToFile(bitmap: Bitmap): String {
    val context = bridge.context
    // Use the cache directory which is accessible to the app and good for temporary files
    val cachePath = File(context.cacheDir, "recolored_images")
    cachePath.mkdirs()

    val file = File(cachePath, "recolor_${System.currentTimeMillis()}.png")
    
    FileOutputStream(file).use { out ->
        // PNG is lossless and preserves quality
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    
    // Return the URI that the web view can use
    return "file://${file.absolutePath}"
}
package com.karla.learningverbs.kotlin.view.profile

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.karla.learningverbs.R
import com.karla.learningverbs.databinding.ActivityUserDetailBinding
import com.karla.learningverbs.kotlin.utils.base.BaseActivity
import com.karla.learningverbs.kotlin.viewmodel.UserDetailViewModel
import com.karla.learningverbs.utils.LearningVerbsDialogGlobal
import java.io.*

class UserDetailActivity : BaseActivity<ActivityUserDetailBinding, UserDetailViewModel>() {

    private var mAuth: FirebaseAuth? = null
    var firebaseUser: FirebaseUser? = null
    private var uri: Uri? = null

    override fun createViewModel(): UserDetailViewModel {
        return ViewModelProvider(this).get(UserDetailViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityUserDetailBinding {
        return ActivityUserDetailBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        inicializateElements()
        chargeInformation()
        binding!!.btnCloseSesion.setOnClickListener {
            LearningVerbsDialogGlobal.dialogGlobal(
                this@UserDetailActivity,
                getString(R.string.title_dialog),
                getString(R.string.msg_dialog),
                { closeSesion() }) { }
        }
        binding!!.btnBack.setOnClickListener { onBackPressed() }
        binding!!.icCamera.setOnClickListener {
            LearningVerbsDialogGlobal.showDialogTakePhoto(this@UserDetailActivity, { var1, dialog ->
                dialog.dismiss()
                ImagePicker.Companion.with(this@UserDetailActivity)
                    .cameraOnly()
                    .crop()
                    .cropSquare()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start()
            }) { var1, dialog ->
                dialog.dismiss()
                ImagePicker.Companion.with(this@UserDetailActivity)
                    .galleryOnly()
                    .crop()
                    .cropSquare()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != 0) {
            if (requestCode == 10 && resultCode == RESULT_OK) {
                uri = data!!.data
                val tempPath = getPathFromInputStreamUri(this, uri)
                Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform())
                    .into(binding!!.imageviewUserAccountProfile)
                viewModel!!.updateProfile(tempPath)
            } else {
                uri = data!!.data
                val tempPath = getPathFromInputStreamUri(this, uri)
                Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform())
                    .into(binding!!.imageviewUserAccountProfile)
                viewModel!!.updateProfile(tempPath)
            }
        }
    }

    private fun chargeInformation() {
        if (firebaseUser!!.displayName != null && !firebaseUser!!.displayName!!.isEmpty()) {
            binding!!.txtUserName.text = firebaseUser!!.displayName
        } else {
            binding!!.txtUserName.text =
                firebaseUser!!.email!!.split("@".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()[0]
        }
        if (firebaseUser!!.photoUrl != null && !firebaseUser!!.photoUrl.toString().isEmpty()) {
            Glide.with(this).load(firebaseUser!!.photoUrl)
                .apply(RequestOptions.circleCropTransform()).into(
                    binding!!.imageviewUserAccountProfile
                )
        }
        binding!!.txtUserEmail.text = firebaseUser!!.email
    }

    private fun inicializateElements() {
        if (mAuth!!.currentUser == null) {
            finish()
        } else {
            firebaseUser = mAuth!!.currentUser
        }
    }

    fun closeSesion() {
        mAuth!!.signOut()
        startActivity(
            Intent(this@UserDetailActivity, CreateAccountActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        finish()
    }

    fun getPathFromInputStreamUri(context: Context, uri: Uri?): String? {
        var inputStream: InputStream? = null
        var filePath: String? = null
        if (uri!!.authority != null) {
            try {
                inputStream = context.contentResolver.openInputStream(uri)
                val photoFile = createTemporalFileFrom(inputStream)
                filePath = photoFile!!.path
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    inputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else {
            onBackPressed()
        }
        return filePath
    }

    @Throws(IOException::class)
    private fun createTemporalFileFrom(inputStream: InputStream?): File? {
        var targetFile: File? = null
        if (inputStream != null) {
            var read: Int
            val buffer = ByteArray(8 * 1024)
            targetFile = createTemporalFile()
            val outputStream: OutputStream = FileOutputStream(targetFile)
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }
            outputStream.flush()
            try {
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return targetFile
    }

    private fun createTemporalFile(): File {
        return File(directoryName, "tempPicture.jpg")
    }

    private val directoryName: String
        private get() {
            val m = packageManager
            val s = packageName
            try {
                val p = m.getPackageInfo(s, 0)
                return p.applicationInfo.dataDir
            } catch (e: PackageManager.NameNotFoundException) {
                Log.w("yourtag", "Error Package name not found ", e)
            }
            return ""
        }
}
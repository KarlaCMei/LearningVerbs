package com.example.learningverbs.userdetail.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.learningverbs.createaccount.CreateAccountActivity;
import com.example.learningverbs.databinding.ActivityUserDetailBinding;
import com.example.learningverbs.utils.LearningVerbsDialogGlobal;
import com.example.learningverbs.userdetail.viewmodel.UserDetailViewModel;
import com.example.learningverbs.utils.BaseActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UserDetailActivity extends BaseActivity<ActivityUserDetailBinding, UserDetailViewModel> {
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private Uri uri;

    @Override
    protected UserDetailViewModel createViewModel() {
        return new ViewModelProvider(this).get(UserDetailViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityUserDetailBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityUserDetailBinding.inflate(layoutInflater);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        inicializateElements();
        chargeInformation();

        binding.btnCloseSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LearningVerbsDialogGlobal.showDialogAccept(UserDetailActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        closeSesion();
                    }
                });
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.icCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LearningVerbsDialogGlobal.showDialogTakePhoto(UserDetailActivity.this, new LearningVerbsDialogGlobal.OnClickDialogListener() {
                    @Override
                    public void onClick(View var1, AlertDialog dialog) {
                        dialog.dismiss();
                        ImagePicker.Companion.with(UserDetailActivity.this)
                                .cameraOnly()
                                .crop()
                                .cropSquare()
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .start();
                    }
                }, new LearningVerbsDialogGlobal.OnClickDialogListener() {
                    @Override
                    public void onClick(View var1, AlertDialog dialog) {
                        dialog.dismiss();
                        ImagePicker.Companion.with(UserDetailActivity.this)
                                .galleryOnly()
                                .crop()
                                .cropSquare()
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .start();
                    }

                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(uri != null){
            if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
                uri = data.getData();
                String tempPath = getPathFromInputStreamUri(this, uri);
                Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform()).into(binding.imageviewUserAccountProfile);
                viewModel.updateProfile(tempPath);
            } else {
                uri = data.getData();
                String tempPath = getPathFromInputStreamUri(this, uri);
                Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform()).into(binding.imageviewUserAccountProfile);
                viewModel.updateProfile(tempPath);
            }
        }
    }

    private void chargeInformation() {
        if (getFirebaseUser().getDisplayName() != null && !getFirebaseUser().getDisplayName().isEmpty()) {
            binding.txtUserName.setText(getFirebaseUser().getDisplayName());
        } else {
            binding.txtUserName.setText(getFirebaseUser().getEmail().split("@")[0]);
        }
        if (getFirebaseUser().getPhotoUrl() != null && !getFirebaseUser().getPhotoUrl().toString().isEmpty()) {

            Glide.with(this).load(getFirebaseUser().getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(binding.imageviewUserAccountProfile);
        }
        binding.txtUserEmail.setText(getFirebaseUser().getEmail());

    }

    private void inicializateElements() {
        if (mAuth.getCurrentUser() == null) {
            finish();
        } else {
            setFirebaseUser(mAuth.getCurrentUser());

        }
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public void closeSesion() {
        mAuth.signOut();
        startActivity(new Intent(UserDetailActivity.this, CreateAccountActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();

    }

    public String getPathFromInputStreamUri(Context context, Uri uri) {
        InputStream inputStream = null;
        String filePath = null;

        if (uri.getAuthority() != null) {
            try {
                inputStream = context.getContentResolver().openInputStream(uri);
                File photoFile = createTemporalFileFrom(inputStream);

                filePath = photoFile.getPath();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            onBackPressed();
        }

        return filePath;
    }

    private File createTemporalFileFrom(InputStream inputStream) throws IOException {
        File targetFile = null;

        if (inputStream != null) {
            int read;
            byte[] buffer = new byte[8 * 1024];

            targetFile = createTemporalFile();
            OutputStream outputStream = new FileOutputStream(targetFile);

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return targetFile;
    }

    private File createTemporalFile() {
        return new File(getDirectoryName(), "tempPicture.jpg");
    }

    private String getDirectoryName() {
        PackageManager m = getPackageManager();
        String s = getPackageName();
        try {
            PackageInfo p = m.getPackageInfo(s, 0);
            return p.applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("yourtag", "Error Package name not found ", e);
        }

        return "";
    }

}

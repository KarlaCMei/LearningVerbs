package com.example.learningverbs.userdetail.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.learningverbs.R;
import com.example.learningverbs.createaccount.createaccountview.CreateAccountActivity;
import com.example.learningverbs.databinding.ActivityHomeBinding;
import com.example.learningverbs.databinding.ActivityUserDetailBinding;
import com.example.learningverbs.home.view.HomeActivity;
import com.example.learningverbs.home.viewmodel.HomeViewModel;
import com.example.learningverbs.listverbs.view.VerbListFragment;
import com.example.learningverbs.login.view.LoginActivity;
import com.example.learningverbs.signup.view.SignUpActivity;
import com.example.learningverbs.tools.LearningVerbsDialogGlobal;
import com.example.learningverbs.userdetail.viewmodel.UserDetailViewModel;
import com.example.learningverbs.utils.BaseActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                LearningVerbsDialogGlobal.showDialogTakePhoto(UserDetailActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImagePicker.Companion.with(UserDetailActivity.this)
                                .cameraOnly()
                                .crop()
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .start();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            //binding.imageviewUserAccountProfile.setImageURI(uri);
            Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform()).into(binding.imageviewUserAccountProfile);
        }else{
            uri = data.getData();
            Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform()).into(binding.imageviewUserAccountProfile);
            //Toast.makeText(getApplicationContext(), "No hay imagen seleccionada", Toast.LENGTH_SHORT).show();
        }
    }

    private void chargeInformation() {
        if (getFirebaseUser().getDisplayName() != null && !getFirebaseUser().getDisplayName().isEmpty()) {
            binding.txtUserName.setText(getFirebaseUser().getDisplayName());
        } else {
            binding.txtUserName.setText(getFirebaseUser().getEmail().split("@")[0]);
        }
        if (getFirebaseUser().getPhotoUrl() != null && !getFirebaseUser().getPhotoUrl().toString().isEmpty()) {

            Glide.with(this).load(uri).apply(RequestOptions.circleCropTransform()).into(binding.imageviewUserAccountProfile);
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

}



/**
 * Codigo para el boton de cerrar sesion
 *
 * private FirebaseAuth mAuth;
 * mAuth = FirebaseAuth.getInstance();
 *
 *
 *     public void closeSesion() {
 *         mAuth.signOut();
 *         Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
 *         startActivity(intent);
 *         finish();
 *     }
 *
 *
 *     ESTE METODO SE MANDA A LLAMAR EN EL ONCLICK DEL BOTON DE CERRAR SESION
 *     closeSesion();
 */
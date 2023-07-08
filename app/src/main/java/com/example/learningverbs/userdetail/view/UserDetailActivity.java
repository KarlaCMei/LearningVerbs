package com.example.learningverbs.userdetail.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.learningverbs.R;
import com.example.learningverbs.createaccount.createaccountview.CreateAccountActivity;
import com.example.learningverbs.databinding.ActivityHomeBinding;
import com.example.learningverbs.databinding.ActivityUserDetailBinding;
import com.example.learningverbs.home.view.HomeActivity;
import com.example.learningverbs.home.viewmodel.HomeViewModel;
import com.example.learningverbs.login.view.LoginActivity;
import com.example.learningverbs.signup.view.SignUpActivity;
import com.example.learningverbs.userdetail.viewmodel.UserDetailViewModel;
import com.example.learningverbs.utils.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class UserDetailActivity extends BaseActivity<ActivityUserDetailBinding, UserDetailViewModel> {
    private FirebaseAuth mAuth;
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

        binding.btnCloseSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeSesion();
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
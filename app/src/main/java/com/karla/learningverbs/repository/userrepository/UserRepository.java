package com.karla.learningverbs.repository.userrepository;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.Tools;
import com.karla.learningverbs.utils.firebase.CustomOnCompleteListener;

public class UserRepository {
    private FirebaseAuth firebaseAuth;
    private static UserRepository instance;

    public UserRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

    public boolean isLogin() {
        return firebaseAuth.getCurrentUser() != null;
    }

    public String getUserId() {
        return (firebaseAuth.getCurrentUser() != null) ? firebaseAuth.getCurrentUser().getUid() : "";

    }

    public Uri getUserUrlImage() {
        return (firebaseAuth.getCurrentUser() != null) ? firebaseAuth.getCurrentUser().getPhotoUrl() : null;
    }

    public void login(CustomOnCompleteListener<AuthResult> onCompleteListener, String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

    public void singUp(CustomOnCompleteListener<AuthResult> onCompleteListener, String email, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

    public void recoverPassword(String email, CustomOnCompleteListener<Void> onCompleteListener) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(onCompleteListener);
    }

    public void updateProfile(String userId, String urlImage, CustomOnCompleteListener<Uri> postListener) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference mPostReference = storage.getReference().child(LearningApplication.getInstance().getApplicationName()).child(userId + ".jpg");
        byte[] data = Tools.getImage(LearningApplication.getMyApplicationContext(), urlImage, 150, 150);
        if (data != null) {
            UploadTask uploadTask = mPostReference.putBytes(data);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful() && task.getException() != null) {
                        throw task.getException();
                    }
                    return mPostReference.getDownloadUrl();
                }
            }).addOnCompleteListener(postListener);
        }
    }

    public void uploadProfile(String url, CustomOnCompleteListener onCompleteListener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(url))
                .build();
        user.updateProfile(profileUpdates).addOnCompleteListener(onCompleteListener);
    }

}

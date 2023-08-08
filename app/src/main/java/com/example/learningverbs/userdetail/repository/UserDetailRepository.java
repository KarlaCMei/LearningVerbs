package com.example.learningverbs.userdetail.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.learningverbs.detailverb.repository.VerbDetailRepository;
import com.example.learningverbs.firebase.CustomOnCompleteListener;
import com.example.learningverbs.model.Verb;
import com.example.learningverbs.tools.LearningApplication;
import com.example.learningverbs.tools.Tools;
import com.example.learningverbs.utils.CustomListEventListener;
import com.example.learningverbs.utils.constants.Constants;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserDetailRepository {
    public static UserDetailRepository instance;
    public static UserDetailRepository getInstance() {
        if (instance == null) instance = new UserDetailRepository();
        return instance;
    }

    public void updateProfile(String userId, String urlImage, CustomOnCompleteListener<Uri> postListener){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference mPostReference = storage.getReference().child("LearningVerbs").child(userId + ".jpg");
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

    public void uploadProfile(String url, CustomOnCompleteListener onCompleteListener){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(url))
                .build();
        user.updateProfile(profileUpdates).addOnCompleteListener(onCompleteListener);
    }

}

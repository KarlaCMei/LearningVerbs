package com.karla.learningverbs.userdetail.repository;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.karla.learningverbs.utils.firebase.CustomOnCompleteListener;
import com.karla.learningverbs.utils.LearningApplication;
import com.karla.learningverbs.utils.Tools;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserDetailRepository {
    public static UserDetailRepository instance;

    public static UserDetailRepository getInstance() {
        if (instance == null) instance = new UserDetailRepository();
        return instance;
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

package com.example.mealio.view.mainScreen.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.MealFireStore;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.presenter.AuthPresenter;
import com.example.mealio.presenter.ProfilePresenter;
import com.example.mealio.view.credentialScreen.AuthView;
import com.example.mealio.view.credentialScreen.AuthenticationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class ProfileFragment extends Fragment   {

    private ImageView userImage;
    private TextView userName, userEmail;
    private Button btnLogout;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageReference storageReference;
    private String imageUrl = "";
    private AuthPresenter presenter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("profile_images");

        userImage = view.findViewById(R.id.user_image);
        userName = view.findViewById(R.id.user_name);
        userEmail = view.findViewById(R.id.user_email);
        btnLogout = view.findViewById(R.id.btn_logout);


        loadUserProfile();

        userImage.setOnClickListener(v -> openFileChooser());

        btnLogout.setOnClickListener(v -> showLogoutDialog());

        return view;
    }

    private void loadUserProfile() {
        if (user != null) {
            userName.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());

            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl()).into(userImage);
            }
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadImageToFirebase();
        }
    }

    private void uploadImageToFirebase() {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(user.getUid() + ".jpg");

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        updateProfileImage(uri.toString());
                    }))
                    .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to upload image", Toast.LENGTH_SHORT).show());
        }
    }

    private void updateProfileImage(String imageUrl) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(imageUrl))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Profile image updated", Toast.LENGTH_SHORT).show();
                        Glide.with(this).load(user.getPhotoUrl()).into(userImage);
                    }
                });
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Logout");
        builder.setMessage("are you sure you want to logout?");
        builder.setPositiveButton("Yes", (dialog, which) -> {

            firebaseAuth.signOut();
            startActivity(new Intent(getActivity(), AuthenticationActivity.class));
            getActivity().finish();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}


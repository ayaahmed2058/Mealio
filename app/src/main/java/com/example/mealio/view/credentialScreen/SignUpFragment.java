package com.example.mealio.view.credentialScreen;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mealio.R;

public class SignUpFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_login_to_signup));
        view.findViewById(R.id.tv_login).setOnClickListener(v -> goToLogin());
        return view;
    }

    private void goToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.transition_signup_to_login));
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, loginFragment)
                .addSharedElement(requireView().findViewById(R.id.tv_login), "transition_text")
                .addToBackStack(null)
                .commit();
    }
}


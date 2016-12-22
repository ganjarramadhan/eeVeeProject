package sense.nl.eevee.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sense.nl.eevee.R;
import sense.nl.eevee.contact.list.ContactList;
import sense.nl.eevee.login.views.LoginView;
import sense.nl.eevee.repository.UserRepository;
import sense.nl.eevee.util.EeveeUtil;

public class LoginFragment extends Fragment implements
        LoginView.OnLoginPageListener, UserRepository.OnLoginEventListener{

    @BindView(R.id.view_login)
    LoginView viewLogin;

    private UserRepository repository;

    public LoginFragment() {
        repository = new UserRepository();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        viewLogin.setOnLoginPageListener(this);
    }

    @Override
    public void onLoginButtonClicked(String email, String pin) {
        if (!EeveeUtil.checkEmail(email)){
            showSnackbar("Invalid email address");
            return;
        }
        repository.login(email, pin, this);
    }

    @Override
    public void onLoginSuccess() {
        showSnackbar("Welcome to Eevee");
        startActivity(new Intent(getActivity(), ContactList.class));
    }

    @Override
    public void onLoginFailed(String message) {
        showSnackbar(message);
    }

    private void showSnackbar(String message){
        Snackbar snackbar = Snackbar
                .make(getView(), message, Snackbar.LENGTH_SHORT);

        snackbar.show();
    }
}

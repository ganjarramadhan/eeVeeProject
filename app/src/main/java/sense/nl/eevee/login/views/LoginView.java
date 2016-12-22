package sense.nl.eevee.login.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sense.nl.eevee.BuildConfig;
import sense.nl.eevee.R;
import sense.nl.eevee.util.EeveeUtil;

/**
 * Created by ahmadmuhsin on 8/2/16.
 */
public class LoginView extends LinearLayout {

    /*
    TODO : Validate email for email text field
    TODO : Validate number only for pin text field
    TODO : Change login button to appearance to rounded
    TODO : Load new image from web for logo
     */

    public interface OnLoginPageListener {
        void onLoginButtonClicked(String email, String pin);
    }

    @BindView(R.id.imv_logo)
    ImageView imvLogo;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_pin)
    EditText edtPin;
    @BindView(R.id.btn_login)
    Button btnLogin;

    boolean validEmail = false;
    boolean validPin = false;

    public void setOnLoginPageListener(OnLoginPageListener onLoginPageListener) {
        this.onLoginPageListener = onLoginPageListener;
    }

    private OnLoginPageListener onLoginPageListener;

    public LoginView(Context context) {
        super(context);
        init();
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_login, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        // load image from url
        Glide.with(getContext())
                .load("https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAARoAAAAJDVlNzk0N2Y1LWIzMTAtNGFmOC1hMjAzLTFhYTM3ZWRlYWZjNQ.png")
                .into(imvLogo);

        btnLogin.setEnabled(false);

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!EeveeUtil.checkEmail(s.toString())){
                    edtEmail.setError("Invalid email");
                    validEmail = false;
                } else {
                    validEmail = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLoginButtonState();
            }
        });

        edtPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() >= 6){
                    validPin = true;
                } else {
                    edtPin.setError("Pin consist of 6 numeric character");
                    validPin = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeLoginButtonState();
            }
        });

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        if (onLoginPageListener != null) {
            onLoginPageListener.onLoginButtonClicked(edtEmail.getText().toString(),
                    edtPin.getText().toString());
        }
    }

    private void changeLoginButtonState(){
        if (validPin && validEmail){
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }

}

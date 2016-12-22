package sense.nl.eevee.contact.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import sense.nl.eevee.R;
import sense.nl.eevee.model.User;

public class ContactDetail extends AppCompatActivity {

    @BindView(R.id.activity_contact_detail_img_avatar)
    ImageView imgAvatar;

    @BindView(R.id.activity_contact_detail_tv_name)
    TextView tvName;

    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_AVATAR = "avatar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);

        String name = getIntent().getStringExtra(KEY_USER_NAME);
        String avatar = getIntent().getStringExtra(KEY_USER_AVATAR);

        if (!name.equals("") || !avatar.equals("")) {
            Glide.with(this)
                    .load(avatar)
                    .into(imgAvatar);
            tvName.setText(name);
        } else {
            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

package sense.nl.eevee.contact.list;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sense.nl.eevee.R;
import sense.nl.eevee.contact.detail.ContactDetail;
import sense.nl.eevee.model.User;
import sense.nl.eevee.repository.UserRepository;
import sense.nl.eevee.util.LinearSpacingItemDecoration;

public class ContactList extends AppCompatActivity implements UserRepository.LoadContactListener,
        ContactListAdapter.ContactItemEventListener {

    @BindView(R.id.activity_contact_list_rv)
    RecyclerView recyclerView;

    UserRepository repoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ButterKnife.bind(this);

        recyclerView.addItemDecoration(new LinearSpacingItemDecoration(this, LinearSpacingItemDecoration.VERTICAL_LIST));

        // init repo model
        repoModel = new UserRepository();

        // load data
        loadContactData();
    }

    private void loadContactData() {
        repoModel.loadContact(this);
    }

    @Override
    public void onLoadContactSuccess(List<User> contactList) {
        ContactListAdapter adapter = new ContactListAdapter(contactList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadContactFailed(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onContactItemClicked(User user) {
        Intent intent = new Intent(this, ContactDetail.class);
        intent.putExtra(ContactDetail.KEY_USER_NAME, user.getName().getTitle() + " " + user.getName().getFirst() + " " +
                user.getName().getLast());
        intent.putExtra(ContactDetail.KEY_USER_AVATAR, user.getPicture().getLarge());
        startActivity(intent);
    }
}

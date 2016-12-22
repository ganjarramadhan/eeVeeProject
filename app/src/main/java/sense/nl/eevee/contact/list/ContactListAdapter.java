package sense.nl.eevee.contact.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sense.nl.eevee.R;
import sense.nl.eevee.model.User;

/**
 * Created by ganjarramadhan on 12/22/16.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {

    private List<User> listUser;
    private ContactItemEventListener listener;

    public ContactListAdapter(List<User> listUser, ContactItemEventListener listener) {
        this.listUser = listUser;
        this.listener = listener;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        ContactListViewHolder holder = new ContactListViewHolder(parent.getContext(), view, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        holder.bindView(listUser.get(position));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ContactListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_contact_img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.item_contact_tv_name)
        TextView tvName;

        private Context context;
        private ContactItemEventListener listener;
        private View view;

        public ContactListViewHolder(Context context, View itemView, ContactItemEventListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.listener = listener;
            this.view = itemView;
        }

        public void bindView(final User user){
            Glide.with(context)
                    .load(user.getPicture().getThumbnail())
                    .into(imgAvatar);
            tvName.setText(user.getName().getTitle() + " " + user.getName().getFirst() + " " +
                    user.getName().getLast());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactItemClicked(user);
                }
            });
        }

    }

    public interface ContactItemEventListener {
        void onContactItemClicked(User user);
    }

}

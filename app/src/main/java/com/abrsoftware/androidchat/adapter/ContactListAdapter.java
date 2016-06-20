package com.abrsoftware.androidchat.adapter;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrsoftware.androidchat.R;
import com.abrsoftware.androidchat.entities.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by json on 15/06/16.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyHolder> {
    private View itemCardView;
    private List<User> valuesContacts;
    private onItemClickListenerItem listenerItem;

    public ContactListAdapter(List<User> contacts, onItemClickListenerItem listenerItem) {
        valuesContacts = contacts;
        this.listenerItem = listenerItem;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        boolean isOnline = valuesContacts.get(position).isOnline();
        String status = isOnline ? "Online" : "Offline";
        int color = isOnline ? holder.itemView.getResources().getColor(R.color.txt_color_online)
                : holder.itemView.getResources().getColor(R.color.txt_color_offline);
        holder.contact = valuesContacts.get(position);
        holder.contactUser.setText(valuesContacts.get(position).getMail());
        holder.contactStatus.setTextColor(color);
        holder.contactStatus.setText(status);
        Glide.with(holder.itemView.getContext())
                .load("")
                .asBitmap()
                .placeholder(R.drawable.contact)
                .into(new BitmapImageViewTarget(holder.contactImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circulaterBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(holder.itemView.getResources(), resource);
                        circulaterBitmapDrawable.setCircular(true);
                    }
                });
    }

    @Override
    public int getItemCount() {
        if (valuesContacts != null) {
            return valuesContacts.size() > 0 ? valuesContacts.size() : 0;
        }
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView contactImage;
        private final TextView contactUser;
        private final TextView contactStatus;
        private User contact;

        public MyHolder(View itemView) {
            super(itemView);
            contactImage = (ImageView) itemView.findViewById(R.id.contactImg);
            contactUser = (TextView) itemView.findViewById(R.id.contactEmail);
            contactStatus = (TextView) itemView.findViewById(R.id.contactStatus);
            itemCardView = itemView;
            contact = null;
        }

        public void setOnclickListener(final User user, final onItemClickListenerItem listenerItem) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerItem.onItemclick(user);
                }
            });
        }

        public void setOnlongClickListener(final User user, final onItemClickListenerItem listenerItem) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listenerItem.onItemlongClickListener(user);
                    return false;
                }
            });
        }
    }

    public interface onItemClickListenerItem {
        void onItemclick(User user);

        void onItemlongClickListener(User user);
    }

    public boolean alreadyInAdapter(User newUser) {
        boolean alAlreadyInAdapter = false;
        for (User user : this.valuesContacts) {
            if (user.getMail().equals(newUser.getMail())) {
                alAlreadyInAdapter = true;
                break;
            }
        }
        return alAlreadyInAdapter;
    }

    public void add(User user) {
        if (!alreadyInAdapter(user)) {
            this.valuesContacts.add(user);
            this.notifyDataSetChanged();
        }
    }

    public void update(User user) {
        int pos = getPositionbyId(user.getMail());
        valuesContacts.set(pos, user);
        this.notifyDataSetChanged();
    }

    public void remove(User user) {
        int pos = getPositionbyId(user.getMail());
        valuesContacts.remove(pos);
        this.notifyDataSetChanged();
    }

    public int getPositionbyId(String userName) {
        int position = 0;
        for (User user : valuesContacts) {
            if (user.getMail().equals(userName)) {
                break;
            }
            position++;
        }
        return position;
    }
}

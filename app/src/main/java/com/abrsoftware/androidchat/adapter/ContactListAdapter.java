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
import com.abrsoftware.androidchat.entities.Contact;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by json on 15/06/16.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyHolder> {
    private View itemCardView;
    private List<Contact> valuesContacts;
    private onItemClickListenerItem listenerItem;

    public ContactListAdapter(List<Contact> contacts, onItemClickListenerItem listenerItem) {
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
        holder.contact = valuesContacts.get(position);
        holder.contactUser.setText(valuesContacts.get(position).getMailContact());
        holder.contactStatus.setText(valuesContacts.get(position).getStatusContact());
        Glide.with(holder.itemView.getContext())
                .load(valuesContacts.get(position).getUrlContactImg())
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

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView contactImage;
        private final TextView contactUser;
        private final TextView contactStatus;
        private Contact contact;

        public MyHolder(View itemView) {
            super(itemView);
            contactImage = (ImageView) itemView.findViewById(R.id.contactImg);
            contactUser = (TextView) itemView.findViewById(R.id.contactEmail);
            contactStatus = (TextView) itemView.findViewById(R.id.contactStatus);
            itemCardView = itemView;
            contactImage.setOnClickListener(this);
            contact = null;
        }

        @Override
        public void onClick(View v) {
            listenerItem.onclick(v);
        }
    }

    public interface onItemClickListenerItem {
        void onclick(View v);
    }
}

package com.runtime.langs.app.mylanguages;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runtime.langs.app.mylanguages.service.LangPayload;
import com.runtime.langs.app.mylanguages.service.ContactsVO;

import java.util.ArrayList;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ContactsVO> contactsList;

    public ContactsListAdapter(Context mContext, LangPayload contactsList) {
        this.mContext = mContext;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactsVO address = contactsList.get(position);
        holder.bindView(address);
    }

    @Override
    public int getItemCount() {
        return contactsList == null ? 0 : contactsList.size();
    }


    public void onItemSelect(ContactsVO contactsVO) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView textViewSubTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textViewSubTitle = (TextView) itemView.findViewById(R.id.textViewSubtitle);

        }

        public void bindView(ContactsVO contactsVO) {

            textView.setText(contactsVO.Name);
            textViewSubTitle.setText(contactsVO.Abstract);

            itemView.setOnClickListener(v -> {
                onItemSelect(contactsVO);
            });
        }
    }
}

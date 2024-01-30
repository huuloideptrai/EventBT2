package org.huuloi.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private LayoutInflater inflater;

    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_contact_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.nameTextView = convertView.findViewById(R.id.nameTextView);
            viewHolder.emailTextView = convertView.findViewById(R.id.emailTextView);
            viewHolder.phoneTextView = convertView.findViewById(R.id.phoneTextView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = getItem(position);

        if (contact != null) {
            viewHolder.nameTextView.setText(contact.getName());
            viewHolder.emailTextView.setText(contact.getEmail());
            viewHolder.phoneTextView.setText(contact.getPhone());
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
        TextView emailTextView;
        TextView phoneTextView;
    }
}

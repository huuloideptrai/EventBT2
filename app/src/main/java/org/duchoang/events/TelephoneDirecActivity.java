package org.duchoang.events;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TelephoneDirecActivity extends AppCompatActivity {

    private ListView contactListView;
    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_phone);

        contactListView = findViewById(R.id.contactListView);

        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(this, contactList);

        contactListView.setAdapter(contactAdapter);


        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                showContactInfo(position);
            }
        });
    }

    private void showContactInfo(int position) {
        Contact selectedContact = contactList.get(position);
        Log.d("ContactInfo", "Name: " + selectedContact.getName());
        Log.d("ContactInfo", "Email: " + selectedContact.getEmail());
        Log.d("ContactInfo", "Phone: " + selectedContact.getPhone());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin khách hàng")
                .setMessage("Họ tên: " + selectedContact.getName() + "\n"
                        + "Email: " + selectedContact.getEmail() + "\n"
                        + "Số điện thoại: " + selectedContact.getPhone())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                Contact newContact = data.getParcelableExtra("newContact");
                if (newContact != null) {
                    contactList.add(newContact);
                    contactAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void onAddContactClick(View view) {
        Intent intent = new Intent(this, CustomerInfoActivity.class);
        startActivityForResult(intent, 1);
    }
}

package org.duchoang.events;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerInfoActivity extends AppCompatActivity {

    private EditText edtCustomerName;
    private EditText edtCustomerEmail;
    private EditText edtCustomerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        edtCustomerName = findViewById(R.id.editTextCustomerName);
        edtCustomerEmail = findViewById(R.id.editTextCustomerEmail);
        edtCustomerPhone = findViewById(R.id.editTextCustomerPhone);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitCustomerInfo();
            }
        });
    }

    private void submitCustomerInfo() {
        String name = edtCustomerName.getText().toString();
        String email = edtCustomerEmail.getText().toString();
        String phone = edtCustomerPhone.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
            Contact newContact = new Contact(name, email, phone);

            Log.d("NewContactInfo", "Name: " + newContact.getName());
            Log.d("NewContactInfo", "Email: " + newContact.getEmail());
            Log.d("NewContactInfo", "Phone: " + newContact.getPhone());

            Intent intent = new Intent();
            intent.putExtra("newContact", newContact);
            setResult(RESULT_OK, intent);

            finish();
        }
    }
}

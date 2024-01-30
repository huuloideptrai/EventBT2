package org.huuloi.events;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtGmail;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtGmail = findViewById(R.id.edtGmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kiemTraDangKy();
            }
        });
    }

    private void kiemTraDangKy() {
        String gmail = edtGmail.getText().toString().trim();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (kiemTraDuLieuDangKy(gmail, username, password, confirmPassword)) {
            hienThiThongBaoDangKyThanhCong();
            luuThongTinDangNhap(username);
            chuyenTrangDangNhap();
        } else {
            hienThiThongBaoLoi("Lỗi", "Thông tin đăng ký không hợp lệ. Vui lòng kiểm tra lại!");
        }
    }

    private boolean kiemTraDuLieuDangKy(String gmail, String username, String password, String confirmPassword) {
        return !gmail.isEmpty() && !username.isEmpty() && !password.isEmpty() && password.equals(confirmPassword);
    }

    private void hienThiThongBaoDangKyThanhCong() {
        Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
    }

    private void hienThiThongBaoLoi(String title, String message) {
        new AlertDialog.Builder(RegisterActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void luuThongTinDangNhap(String username) {
        SharedPreferences preferences = getSharedPreferences("thong_tin_dang_nhap", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    private void chuyenTrangDangNhap() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginsClick(View view) {
        chuyenTrangDangNhap();
    }
}

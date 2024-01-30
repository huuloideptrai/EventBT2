package org.huuloi.events;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        String savedUsername = layThongTinDangNhap();
        if (!TextUtils.isEmpty(savedUsername)) {
            edtUsername.setText(savedUsername);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kiemTraDangNhap();
            }
        });
    }

    private void kiemTraDangNhap() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showAlertDialog("Đăng nhập lỗi", "Không được để trống tài khoản hoặc mật khẩu. Vui lòng nhập đầy đủ thông tin!");
        } else if (kiemTraTaiKhoanMatKhau(username, password)) {
            showAlertDialog("Chúc mừng", "Đăng nhập thành công!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    chuyenTrangTelephoneDirec();
                }
            });
        } else {
            showAlertDialog("Đăng nhập lỗi", "Tài khoản hoặc mật khẩu nhập sai. Vui lòng thử lại!");
        }
    }

    private boolean kiemTraTaiKhoanMatKhau(String username, String password) {
        return username.equals("admin") && password.equals("123456789");
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showAlertDialog(String title, String message, DialogInterface.OnClickListener positiveClickListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", positiveClickListener)
                .setCancelable(false)
                .show();
    }

    private void chuyenTrangTelephoneDirec() {
        Intent intent = new Intent(MainActivity.this, TelephoneDirecActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCTRegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private String layThongTinDangNhap() {
        SharedPreferences preferences = getSharedPreferences("thong_tin_dang_nhap", MODE_PRIVATE);
        return preferences.getString("username", "");
    }
}

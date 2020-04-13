package uph.com.final_project_mpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private android.widget.EditText password_input;
    private AppCompatCheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button logout = findViewById(R.id.btnCancelLogin);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void login (View view){
        String username = ((MyApplication)this.getApplication()).getUsername();
        String password = ((MyApplication)this.getApplication()).getPassword();

        EditText user = findViewById(R.id.username_input);
        EditText pass = findViewById(R.id.password_input);

        if(!user.getText().toString().equals("") && !pass.getText().toString().equals("")){
            if (user.getText().toString().equals(username) && pass.getText().toString().equals(password)){
                Intent intent = new Intent(getApplicationContext(), AspirationListActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "USERNAME ATAU PASSWORD SALAH",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "ISI SEMUA KOLOM !",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText pass = findViewById(R.id.password_input);
        pass.setText("");
    }
}

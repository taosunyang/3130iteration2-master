package com.kcb.timeTable;
//author: Duncan Duan
//finish date: Feb/25/2019
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter=5;
    private TextView userRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    //link the private variable with xml file
        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Info=(TextView)findViewById(R.id.tvInfo);
        Login=(Button)findViewById(R.id.btnLogin);
        userRegistration=(TextView)findViewById(R.id.tvRegister);

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, RegisterationActivity.class));
            }
        });

    }
    private void validate(String userName, String userPassword){
        if((userName.equals("Admin"))&& (userPassword.equals("1234"))||(userName.equals("kduan"))&& (userPassword.equals("dk123"))){
            Intent intent=new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
        }else{
            counter--;

            Info.setText("No of attempts remaining: "+String.valueOf(counter));

            if(counter==0){
                Login.setEnabled(false);
            }
        }

    }

}

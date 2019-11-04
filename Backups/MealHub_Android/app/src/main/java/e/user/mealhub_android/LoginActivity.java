package e.user.mealhub_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    private Button Connect;
    private TextView SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Connect=(Button)findViewById(R.id.Connect);
        SignUp=(TextView) findViewById(R.id.signup);

        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConnect();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp();
            }
        });


    }

    public void onConnect()
    {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);

    }

    public void onSignUp()
    {
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);

    }
}

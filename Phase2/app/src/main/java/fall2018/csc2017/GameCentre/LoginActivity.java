package fall2018.csc2017.GameCentre;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The login activity class
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * The layout variables
     */
    private RelativeLayout rellay1, rellay2;

    /**
     * The handler variable
     */
    private Handler handler = new Handler();

    /**
     * The editable text
     */
    private EditText email, password;

    /**
     * The textview for forgot password
     */
    private TextView forgotPassword;

    /**
     * The counter for the number of times we try and login
     */
    private int counter = 5;

    /**
     * The progress bar
     */
    private ProgressBar progressBar;

    /**
     * The buttons for logging in and registering
     */
    private Button loginBtn,registrationBtn;

    /**
     * The firebase authentication and user variables
     */
    private FirebaseAuth auth;
    private FirebaseUser user;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 2000);
        initializeViews();
        checkIfLoggedOn();
        loginClickListener();
        forgotPasswordClickListener();
        registrationBtnClickListener();

    }

    private void registrationBtnClickListener() {
        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

    }

    private void forgotPasswordClickListener() {
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, PasswordActivity.class));
            }
        });
    }

    /**
     * Initialize the views for this page
     */
    public void initializeViews()
    {
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.passPrompt);
        loginBtn = findViewById(R.id.loginBtn);
        registrationBtn = findViewById(R.id.registrationBtn);
        forgotPassword = findViewById(R.id.btnForgotPassword);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        user = FirebaseAuth.getInstance().getCurrentUser();

    }

    /**
     * Check if the user is logged in
     */
    public void checkIfLoggedOn()
    {
        if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, StartingActivity.class));
        }
    }

    public void loginClickListener()
    {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });
    }

    /**
     * Validate if the user info is valid, and respond accordingly depending on if it is or not.
     * @param userName the username entered
     * @param userPassword the password entered
     */
    private void validate(String userName, String userPassword) {

        if (email.getText().toString().equals("") && password.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Blanks not allowed!", Toast.LENGTH_SHORT).show();
        }
        else {
            progressBar.setTooltipText("Please be patient as login verifies!");
            progressBar.setVisibility(View.VISIBLE);
            final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LoginActivity.this);

            auth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(getApplicationContext(), StartingActivity.class));
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        counter--;
                        progressBar.setVisibility(View.INVISIBLE);
                        if (counter == 0) {
                            alertBuilder.setMessage("You have attempted to log in to many times! \n\nLogging is disabled!")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    });
                            AlertDialog alert = alertBuilder.create();
                            alert.setTitle("Alert !!!");
                            alert.show();
                            loginBtn.setEnabled(false);
                        }
                    }
                }
            });
        }
    }
}

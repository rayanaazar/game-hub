package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The Password activity_tiles_scores class
 */
public class PasswordActivity extends AppCompatActivity {

    /**
     * The editable text for the email
     */
    private EditText passwordEmail;

    /**
     * The button to reset password
     */
    private Button resetPasswordBtn;

    /**
     * The firebase authentication object
     */
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        initializeViews();
        resetPassword();
    }

    /**
     * Initialize the views for this page
     */
    private void initializeViews()
    {
        passwordEmail = findViewById(R.id.passwordEmail);
        resetPasswordBtn = findViewById(R.id.sendBtn);
        auth = FirebaseAuth.getInstance();

    }

    /**
     * Reset the password with given data
     */
    private void resetPassword()
    {
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(PasswordActivity.this, "Please enter the email used to create a profile.", Toast.LENGTH_SHORT).show();
                }else{
                    auth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this, "Password Reset Email sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(PasswordActivity.this, "Error in Sending Password Reset Email!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}

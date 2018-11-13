package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * The sign_in activity for the sliding puzzle tile game.
 */
public class RegistrationActivity extends AppCompatActivity {
    /**
     * All text obtained from the screen
     */
    private EditText usrnm, email, password, confirmPassword;

    /**
     * The register button
     */
    private Button regButton;

    /**
     * The Firebase authentication instance
     */
    private FirebaseAuth auth;

    /**
     * Strings of given fields
     */
    private String emailStr, usrnmStr, passwordStr;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupViews();

        auth = FirebaseAuth.getInstance();
        register();

    }

    /**
     * Registers a user through firebase
     */
    private void register() {
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    auth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                pushData();
                                auth.signOut();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, StartingActivity.class));
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });

    }

    /**
     * Checks if a user if registered in our database, and that the data given is valid
     * @return true iff user info is valid
     */
    private Boolean check(){
        Boolean rslt = false;

        usrnmStr = usrnm.getText().toString().trim();
        passwordStr = password.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        String confirmPasswordStr = confirmPassword.getText().toString().trim();

        if(usrnmStr.isEmpty() || passwordStr.isEmpty() || emailStr.isEmpty() || !passwordStr.equals(confirmPasswordStr)) {
            Toast.makeText(this, "Please make sure to enter correct information!", Toast.LENGTH_SHORT).show();
        } else {
            rslt = true;
        }
        return rslt;
    }

    /**
     * Sets up the views
     */
    private void setupViews(){
        usrnm = findViewById(R.id.username);
        email = findViewById(R.id.regEmail);
        password = findViewById(R.id.passPrompt);
        regButton = findViewById(R.id.btnRegister);
        confirmPassword = findViewById(R.id.confirmPass);
    }

    /**
     * Pushes user data to the database
     */
    private void pushData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(Objects.requireNonNull(auth.getUid()));
        Profile profile = new Profile(emailStr,usrnmStr);
        reference.setValue(profile);
    }



}
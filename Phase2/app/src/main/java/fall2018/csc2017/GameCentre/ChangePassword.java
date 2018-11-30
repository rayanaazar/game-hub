package fall2018.csc2017.GameCentre.ui.menu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import fall2018.csc2017.GameCentre.R;

public class ChangePassword extends AppCompatActivity
{
    FirebaseAuth auth;
    TextInputEditText inputEditText;
    Button changePassBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        auth = FirebaseAuth.getInstance();
        inputEditText = findViewById(R.id.changePassPrompt);
        changePassBtn = findViewById(R.id.changePassBtn);
        passButtonOnClickListener();


    }

    public void passButtonOnClickListener()
    {
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassBtn.setVisibility(View.GONE);
                Objects.requireNonNull(auth.getCurrentUser())
                        .updatePassword(inputEditText.toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),
                                            "Password Changed!", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),
                                            "Password Couldn't Be Changed!",
                                            Toast.LENGTH_SHORT).show();
                                    changePassBtn.setVisibility(View.VISIBLE);
                                }
                            }
                        });
            }
        });
    }

}
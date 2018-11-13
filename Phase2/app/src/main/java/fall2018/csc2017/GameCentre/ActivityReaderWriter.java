package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

/**
 * The general class for reading and writing files
 */
public abstract class ActivityReaderWriter extends AppCompatActivity {

    FirebaseUser user;

    /**
     * Save the board manager to fileName.
     *
     * @param moveStack the stack of moves to be pushed to storage
     */
    public void save(Stack<Board> moveStack) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        StorageReference sRef = FirebaseStorage.getInstance().getReference();
        StorageReference userRef = sRef.child(user.getUid());

        try {
            // Make the file
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("moveStack.ser")
            ));
            oos.writeObject(moveStack);
            oos.close();

            // Upload the file
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(new File("moveStack.ser"))
            );
            UploadTask uploadTask = userRef.putStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the board manager from fileName.
     */
    public Stack<Board> load() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        StorageReference sRef = FirebaseStorage.getInstance().getReference();
        StorageReference userRef = sRef.child(user.getUid());

        try {
            StorageReference movesRef = userRef.child("moveStack.ser");
            File local = File.createTempFile("moveStack", "ser");
            movesRef.getFile(local);

            InputStream inputStream = this.openFileInput("moveStack.ser");
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                Stack<Board> moveStack = (Stack<Board>) input.readObject();
                inputStream.close();
                return moveStack;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

}

package fall2018.csc2017.GameCentre.games;

/**
 * The general class for reading and writing files
 */

//TODO make this work with each game that will parse
public abstract class FirebaseRTStorage {

    public void save()
    {}

//  Each game has to implement this method to prepare to write to database
//    - Example: Sliding Tiles will use this to get the order of the tiles by id into a comma seperated string
//    - Example: Tic Tac Toe will use this to get a string representation of the board
    public abstract void prepareForSave();

    public void load()
    {}

//    This is used after running load()
//    each game will get
    public abstract void convertIntoBoard();


//TODO USE ONLY IF NEEDED
//    FirebaseUser user;
//
//    /**
//     * Save the board manager to fileName.
//     *
//     * @param moveStack the stack of moves to be pushed to storage
//     */
//    public void save(Stack<Board> moveStack) {
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        StorageReference sRef = FirebaseStorage.getInstance().getReference();
//        StorageReference userRef = sRef.child(user.getUid());
//
//        try {
//            // Make the file
//            ObjectOutputStream oos = new ObjectOutputStream(
//                    new FileOutputStream(new File("moveStack.ser")
//            ));
//            oos.writeObject(moveStack);
//            oos.close();
//
//            // Upload the file
//            ObjectInputStream inputStream = new ObjectInputStream(
//                    new FileInputStream(new File("moveStack.ser"))
//            );
//            UploadTask uploadTask = userRef.putStream(inputStream);
//            inputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//    /**
//     * Load the board manager from fileName.
//     */
//    public Stack<Board> load() {
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        StorageReference sRef = FirebaseStorage.getInstance().getReference();
//        StorageReference userRef = sRef.child(user.getUid());
//
//        try {
//            StorageReference movesRef = userRef.child("moveStack.ser");
//            File local = File.createTempFile("moveStack", "ser");
//            movesRef.getFile(local);
//
//            InputStream inputStream = this.openFileInput("moveStack.ser");
//            if (inputStream != null) {
//                ObjectInputStream input = new ObjectInputStream(inputStream);
//                Stack<Board> moveStack = (Stack<Board>) input.readObject();
//                inputStream.close();
//                return moveStack;
//            }
//        } catch (FileNotFoundException e) {
//            Log.e("login activity_tiles_scores", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity_tiles_scores", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("login activity_tiles_scores", "File contained unexpected data type: " + e.toString());
//        }
//        return null;
//    }

}

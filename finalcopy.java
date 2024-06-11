import javax.swing.*; //importing java swing for GUI
import java.awt.*; //importing java awt
import java.awt.event.*; //importing java event
import java.io.*; //importing java io
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Arrays;
public class finalcopy
{
    private JTextField text  = new JTextField("Type Here"); //Text field where the note is put into 
    private JFrame homeFrame = new JFrame("Memorandum"); //Home frame with all main function buttons (open note, delete note, ect.)
    private JFrame nameNoteFrame = new JFrame("Note Name"); //Note name frame where the user names the text file
    private JFrame noteFrame = new JFrame("New Note"); //Note frame where the user types in what goes inside the text file
    private JFrame welcomeFrame = new JFrame("Welcome To Memorandum"); //Welcome frame where user types out directory 
    private JFrame deleteFrame = new JFrame("Delete Note"); //Delete frame where user can delete a text file 
    private JFrame editFrame = new JFrame("Edit Note"); //Edit frame where user can edit a previous made text file
    private JButton newNoteButton = new JButton("New Note"); //Button on home frame to create a new note
    private JButton saveNoteButton = new JButton("Save Note"); //Button to confirm the save of the text in the note
    private JButton saveNoteButton1 = new JButton("Save Note"); //Button to confirm the save of the text in the edited note
    private JButton deleteNoteButton = new JButton("Delete Note"); //Button on the home frame that take the user to the delete note frame
    private JButton backButton = new JButton("Back"); //Back button goes back to the welcome frame
    private JTextField noteName = new JTextField("Enter Note Name Here"); //Text field where the user enters the note name
    private JTextField computerName = new JTextField ("Enter Computer Name"); //Text field where user enters their computer names
    private JTextField folderName = new JTextField("Enter Folder Name"); //Text field where user enters their folder name
    private JTextField reopenNoteText = new JTextField(""); //Text field to hold text from choosen text file
    private JButton nextButton = new JButton ("Next"); //Next button to continue from welcome frame to home frame
    private JButton saveNoteName = new JButton ("Save Name"); //Button to confirm the save of the name of the text file
    private JButton saveButton = new JButton("Save"); //Save button to confirm the save of the directory
    private JButton deleteButton = new JButton("Delete Note"); //Button to confirm the deletion of the right note
    private JButton backToHome = new JButton("Back To Home"); //Button to take the user back to the home frame
    private JButton openNote = new JButton("Open Note"); //Button to go to the open note frame
    //private JButton setPasswordButton = new JButton("Set Password");
    //private JPasswordField pwfield = new JPasswordField();
    //private JPasswordField accessPwField = new JPasswordField();
    private File file;
    private String compName;
    private String foldName;
    private String backFileName;
    private String docName;
    private StringBuilder sb = new StringBuilder();
    //private boolean locked = false;
    public static void main(String[] args){ //main method to create the app
        NoteApp x = new NoteApp(); //declaration of NoteApp
        x.welcome();
    }
    public void welcome(){ //Welcome method which executes the method frame to create directory for user 
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        welcomeFrame.setSize(1000,1000); //set size of frame
        welcomeFrame.setLayout(new FlowLayout()); //set layout the frame will take
        welcomeFrame.setVisible(true); //make the frame visiable to the user
        JPanel panel = new JPanel(); //creating a panel inside the frame to add all the button and textfields
        panel.setLayout(new FlowLayout()); // adding the layout to the panel
        welcomeFrame.add(panel); //adding the panel to the frame
        panel.add(computerName); //adding a text field to the panel
        panel.add(folderName);
        panel.add(saveButton); //adding a button to the panel
        panel.add(nextButton);
        saveButton.addActionListener(new ActionListener(){ //action to add action to the button when clicked
                public void actionPerformed(ActionEvent a){
                    compName = computerName.getText(); //getting text from the computerName textfield and putting it into a String
                    foldName = folderName.getText(); //getting text from the folderName textfield putting it into a String
                    backFileName = ("/Users/" + compName + "/Documents/" + foldName + "/"); //creating a string with a portion of the directory
                }
        });
        nextButton.addActionListener(new ActionListener(){ //action to next button in order to move onto home frame
                public void actionPerformed(ActionEvent d){
                    home();
                }
        });
    }
    public void home() { //method for creating the home frame
        welcomeFrame.dispose(); //closes the welcome frame
        homeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        homeFrame.setSize(1000,1000);
        homeFrame.setLayout(new FlowLayout());
        homeFrame.setVisible(true); 
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        homeFrame.add(panel);
        panel.add(newNoteButton); //Add new note button to panel
        newNoteButton.addActionListener(new ActionListener(){ //if new note button is pressed note name method will be called
                public void actionPerformed(ActionEvent e){ //creates a action for the action event to call the method
                    noteName();
                }
        });
        panel.add(deleteNoteButton);
        deleteNoteButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent d){
                    deleteNote();
                }
        });
        panel.add(openNote);
        openNote.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent d){
                    openNote();
                }
        });
        panel.add(backButton);
        backButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent d){
                    homeFrame.dispose();
                    welcome();
                }
        });
    }
    public void noteName(){ //creates a frame for name the text file
        homeFrame.dispose();
        nameNoteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameNoteFrame.setLayout(new FlowLayout());
        nameNoteFrame.setSize(1000,1000);
        nameNoteFrame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        nameNoteFrame.add(panel);
        panel.add(noteName);
        panel.add(saveNoteName);
        saveNoteName.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent s){
                    docName = noteName.getText();
                    noteName.setText(docName);
                    newNote(); //calling the new note method
                }
        });
    }
    public void newNote(){ //method that make a frame where the user can create a note and make the text inside the text file 
        nameNoteFrame.dispose();
        noteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        noteFrame.setLayout(new FlowLayout());
        noteFrame.setSize(1000,1000);
        noteFrame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        noteFrame.add(panel);
        panel.add(text);
        text.setPreferredSize(new Dimension(300, 20)); //change dimension of text field
        panel.add(saveNoteButton);
        panel.add(backToHome);
        saveNoteButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent a){
                    String note = text.getText(); 
                    text.setText(note); //sets text field to what is in the string 
                    addNote(text.getText()); //calls addNote method with the new text field inside)
                }
        });
        backToHome.addActionListener(new ActionListener(){ //back to home button which allows the user to click the button and go back to home
                public void actionPerformed(ActionEvent d){
                    noteFrame.dispose();
                    homeFrame.setVisible(true);
                }
        });
    }
    public void addNote(String content){ //method of creating a text file
        try{ //try and catch to better understand error and which errors were made
            String docName = noteName.getText(); //geting note name
            File f = new File(backFileName + docName + ".txt"); //creating a file with the proper directory
            FileWriter fw = new FileWriter(f, false); //creates a file writer to write the text 
            PrintWriter pw = new PrintWriter(fw); //creates a print writer to print the text onto the text file
            pw.println(content); //printing the text onto the text file
            pw.close(); //closing the print writer
        }catch(IOException ex) {
            ex.printStackTrace(); //error catcher
        }
    }
    public void deleteNote(){ //method to create frame in order to delete note
        homeFrame.dispose();
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setLayout(new FlowLayout());
        deleteFrame.setSize(1000,1000);
        deleteFrame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        deleteFrame.add(panel);
        panel.add(noteName);
        panel.add(deleteButton);
        panel.add(backToHome);
        deleteButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent c){
                    try  
                    {   
                        docName = noteName.getText(); //get file name from note name text field
                        File f = new File(backFileName + docName + ".txt"); //file to be delete  
                        if(f.exists()) //returns Boolean value depend on if the file exists
                        {  
                            f.delete(); //deleteling the file  
                        }  
                        else  
                        {  
                            System.out.println("failed: does not exist");  //file doesn't exist prints this 
                        }  
                    }  
                    catch(Exception e) 
                    {  
                        e.printStackTrace();  
                    }  
                }
        });
        backToHome.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent d){
                    deleteFrame.dispose();
                    homeFrame.setVisible(true);
                }
        });
    }
    public void openNote(){ //method for reopening notes
        JFileChooser fileChooser = new JFileChooser(); //file chooser which allows the user to choose directory through mini panel
        int reponse = fileChooser.showOpenDialog(null); //set the file choosen to an integer value (1 for file; 0 for everything else)
        if(reponse == JFileChooser.APPROVE_OPTION){ //checking to see which action is executed
            file = new File(fileChooser.getSelectedFile().getAbsolutePath()); //gets path for file
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file)); //creating a buffered reader in order to read file
                String line; //declartion of string
                while ((line = reader.readLine()) != null) { //continously reads and prints file until file is done
                    sb.append(line).append("\n"); //prints file
                }
                reader.close(); //closes buffered reader
                reopenNoteText.setText(sb.toString()); //setting the text field to the new string made
                editNote(); //calling the edit note method
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void editNote(){ //edit note method which allows the user to edit the note made before
        homeFrame.dispose();
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setLayout(new FlowLayout());
        editFrame.setSize(1000,1000);
        editFrame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        editFrame.add(panel);
        reopenNoteText.setText(sb.toString());
        panel.add(reopenNoteText);
        reopenNoteText.setPreferredSize(new Dimension(300, 20));
        panel.add(saveNoteButton1);
        saveNoteButton1.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent a){
                    String s = reopenNoteText.getText();
                    try {
                        FileWriter fw = new FileWriter(file); //creating a file wrriter
                        fw.write(s); //writing new lines on to th file
                        fw.close(); //closing file writer
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editFrame.dispose();
                    homeFrame.setVisible(true);
                }
        });
    }
    public void encryptFile(String filePath) {
        try {
            String password = JOptionPane.showInputDialog(null, "Enter password:");
            if (password == null || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.");
                return;
            }

            byte[] fileContent = Files.readAllBytes(Paths.get(filePath)); 
            byte[] key = Arrays.copyOf(password.getBytes(StandardCharsets.UTF_8), 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES"); // used to encrypt or decrypt parts of a byte array
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);// encrypting mode

            byte[] encryptedContent = cipher.doFinal(fileContent);
            Files.write(Paths.get(filePath), encryptedContent);

            JOptionPane.showMessageDialog(null, "Password Created!");
            locked = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void decryptFile(String filePath) {
        try {
            String password = JOptionPane.showInputDialog(null, "Enter password:");
            if (password == null || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.");
                return;
            }

            byte[] encryptedContent = Files.readAllBytes(Paths.get(filePath));
            byte[] key = Arrays.copyOf(password.getBytes(StandardCharsets.UTF_8), 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decryptedContent = cipher.doFinal(encryptedContent);
            Files.write(Paths.get(filePath), decryptedContent);

            JOptionPane.showMessageDialog(null, "Welcome!");
            locked = false;
            editNote();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Incorrect Password");
        }
    }
    public void password(File a) {
        String filePath = a.getAbsolutePath();

        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option:",
                "Password Lock System",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Encrypt", "Decrypt"},
                "Encrypt");

        if (choice == 0) {
            encryptFile(filePath);
        } else if (choice == 1) {
            decryptFile(filePath);
        }
    }
}



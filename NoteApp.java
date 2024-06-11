import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Arrays;
public class NoteApp{
    private JTextField text  = new JTextField("Type Here");
    private JFrame homeFrame = new JFrame("Memorandum");
    private JFrame nameNoteFrame = new JFrame("Note Name");
    private JFrame noteFrame = new JFrame("New Note");
    private JFrame welcomeFrame = new JFrame("Welcome To Memorandum");
    private JFrame deleteFrame = new JFrame("Delete Note");
    private JFrame editFrame = new JFrame("Edit Note");
    private JButton newNoteButton = new JButton("New Note");
    private JButton saveNoteButton = new JButton("Save Note");
    private JButton saveNoteButton1 = new JButton("Save Note");
    private JButton deleteNoteButton = new JButton("Delete Note");
    private JButton backButton = new JButton("Back");
    private JTextField noteName = new JTextField("Enter Note Name Here");
    private JTextField computerName = new JTextField ("Enter Computer Name");
    private JTextField folderName = new JTextField("Enter Folder Name");
    private JTextField reopenNoteText = new JTextField(" ");
    private JButton nextButton = new JButton ("Next");
    private JButton saveNoteName = new JButton ("Save Name");
    private JButton saveButton = new JButton("Save");
    private JButton deleteButton = new JButton("Delete Note");
    private JButton backToHome = new JButton("Back To Home");
    private JButton openNote = new JButton("Open Note");
    private JButton setPasswordButton = new JButton("Set Password");
    private JPasswordField pwfield = new JPasswordField();
    private JPasswordField accessPwField = new JPasswordField();
    //private JButton fileDirect = new JButton("Choose File Location");
    private File file;
    private String compName;
    private String foldName;
    private String backFileName;
    private String docName;
    private StringBuilder sb = new StringBuilder();
    private boolean locked = false;
    public static void main(String[] args){
        NoteApp x = new NoteApp();
        x.welcome();
    }

    public void welcome(){
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setSize(550,500);
        welcomeFrame.setLayout(new FlowLayout());
        welcomeFrame.setVisible(true); 
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        welcomeFrame.add(panel);
        //panel.add(fileDirect);
        //fileDirect.addActionListener(new ActionListener() {
        //public void actionPerformed(ActionEvent e) {
        //JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //int option = fileChooser.showOpenDialog(null);
        //if(option == JFileChooser.APPROVE_OPTION){
        //File file = fileChooser.getSelectedFile();
        //String x = file.getName();
        //backFileName = x; 
        //}
        //}
        //});
        panel.add(computerName);
        panel.add(folderName);
        panel.add(saveButton);
        panel.add(nextButton);
        saveButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent a){
                    compName = computerName.getText();
                    foldName = folderName.getText();
                    backFileName = ("/Users/" + compName + "/Documents/" + foldName + "/");
                    System.out.println(backFileName);
                }
            });
        nextButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent d){
                    home();
                }
            });
    }

    public void home() {
        welcomeFrame.dispose();
        homeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        homeFrame.setSize(550,500);
        homeFrame.setLayout(new FlowLayout());
        homeFrame.setVisible(true); //to make frame visible
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        homeFrame.add(panel);
        panel.add(newNoteButton);
        newNoteButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
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

    public void noteName(){
        homeFrame.dispose();
        nameNoteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameNoteFrame.setLayout(new FlowLayout());
        nameNoteFrame.setSize(400, 300);
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
                    newNote();
                }
            });
    }

    public void newNote(){
        nameNoteFrame.dispose();
        noteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        noteFrame.setLayout(new FlowLayout());
        noteFrame.setSize(400, 300);
        noteFrame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        noteFrame.add(panel);
        panel.add(text);
        text.setPreferredSize(new Dimension(300, 100)); //change dimension of text
        panel.add(saveNoteButton);
        panel.add(backToHome);
        saveNoteButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent a){
                    String note = text.getText();
                    text.setText(note);
                    addNote(text.getText());
                }
            });
        backToHome.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent d){
                    noteFrame.dispose();
                    homeFrame.setVisible(true);
                }
            });
    }

    public void addNote(String content){
        try{
            System.out.println("Inside:" + content);
            String docName = noteName.getText();
            File f = new File(backFileName + docName + ".txt");
            FileWriter fw = new FileWriter(f, false);  
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.close();
            System.out.println("worked");
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteNote(){
        homeFrame.dispose();
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setLayout(new FlowLayout());
        deleteFrame.setSize(400, 300);
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
                        docName = noteName.getText();
                        File f = new File(backFileName + docName + ".txt");           //file to be delete  
                        if(f.exists())                      //returns Boolean value  
                        {  
                            f.delete();   //getting and printing the file name  
                        }  
                        else  
                        {  
                            System.out.println("failed: does not exist");  
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

    public void openNote(){
        JFileChooser fileChooser = new JFileChooser();
        int reponse = fileChooser.showOpenDialog(null);
        if(reponse == JFileChooser.APPROVE_OPTION){
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                reader.close();
                reopenNoteText.setText(sb.toString());
                if(locked){
                        decryptFile(file.getAbsolutePath());
                    }else{
                        encryptFile(file.getAbsolutePath());
                    }
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editNote(){
        homeFrame.dispose();
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setLayout(new FlowLayout());
        editFrame.setSize(400, 300);
        editFrame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        editFrame.add(panel);
        reopenNoteText.setText(sb.toString());
        panel.add(reopenNoteText);
        panel.add(saveNoteButton1);
        saveNoteButton1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent a){
                    String s = reopenNoteText.getText();
                    System.out.println("Note: " + s);
                    try {
                        FileWriter fw = new FileWriter(file);
                        fw.write(s);
                        fw.close();
                        System.out.println("File content has been updated");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editFrame.dispose();
                    homeFrame.setVisible(true);
                }
            });
        panel.add(setPasswordButton);
        setPasswordButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    if(locked){
                        decryptFile(file.getAbsolutePath());
                    }else{
                        encryptFile(file.getAbsolutePath());
                    }
                    
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

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedContent = cipher.doFinal(fileContent);
            Files.write(Paths.get(filePath), encryptedContent);

            JOptionPane.showMessageDialog(null, "File encrypted successfully!");
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

            JOptionPane.showMessageDialog(null, "File decrypted successfully!");
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


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.io.File;

public class FileEncryption {

    public static void encryptFile(String filePath) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String filePath) {
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
        } catch (Exception e) {
            e.printStackTrace();
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

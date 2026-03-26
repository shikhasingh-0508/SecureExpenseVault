import java.util.Scanner;

public class Security {
    // The master password to unlock the application
    private static final String MASTER_PASSWORD = "s_singh05";

    public static boolean authenticate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Master Password to Unlock Vault: ");
        String attempt = sc.nextLine();
        return attempt.equals(MASTER_PASSWORD);
    }
    
    // Simple XOR Encryption to scramble and unscramble the text
    public static String encryptDecrypt(String data) {
        char key = 'V'; 
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            output.append((char) (data.charAt(i) ^ key));
        }
        return output.toString();
    }
}
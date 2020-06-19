package client;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores config, including encrypted database passwords
 * Proudly copied from https://stackoverflow.com/a/12981202/7193339
 */
public class Config {

    //Cipher salt.
    private static final byte[] SALT = {
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
            (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,};

    //Ciphering (encryption and decryption) password/key.
    private static final char[] PASSWORD = "Unauthorized_Personel_Is_Unauthorized".toCharArray();

    //These will be used as the source of the configuration file's stored attributes.
    private static final Map<String, String> COMMON_ATTRIBUTES = new HashMap<>();
    private static final Map<String, char[]> SECURE_ATTRIBUTES = new HashMap<>();

    //Directory and file names
    private static final File RESOURCE_DIR = new File("src/main/resources");
    private static final String GENERAL_CONFIG = "general.properties";
    private static final String DATABASE_CONFIG = "database.properties";


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        //Set common attributes.
        COMMON_ATTRIBUTES.put("CsvExport", "OnEveryTransaction");

        /*
         * Set secure attributes.
         * NOTE: Ignore the use of Strings here, it's being used for convenience only.
         * In real implementations, JPasswordField.getPassword() would send the arrays directly.
         */
        SECURE_ATTRIBUTES.put("Username", "Hypothetical".toCharArray());
        SECURE_ATTRIBUTES.put("Password", "LetMePass_Word".toCharArray());

        /*
         * For demosntration purposes, I make the three encryption layer-levels I mention.
         * To leave no doubt the code works, I use real file IO.
         */
        //File without encryption.
        create_EncryptedFile(GENERAL_CONFIG, COMMON_ATTRIBUTES, new HashMap<>(), 0);
        //File with encryption to secure attributes only.
        create_EncryptedFile(DATABASE_CONFIG, new HashMap<>(), SECURE_ATTRIBUTES, 1);


        /*
         * Show contents of all three encryption levels, from file.
         */
        System.out.println("NO ENCRYPTION: \n" + readFile_NoDecryption(GENERAL_CONFIG) + "\n\n\n");
        System.out.println("SINGLE LAYER ENCRYPTION: \n" + readFile_NoDecryption(DATABASE_CONFIG) + "\n\n\n");


        //Decrypt  (file content)
        String decryptedContent = readFile_NoDecryption(DATABASE_CONFIG);
        System.out.println("READ decrypted\n" + decryptedContent + "\n\n\n");
        //Decrypt second layer (secure data).
        for (String line : decryptedContent.split("\n")) {
            String[] pair = line.split(": ", 2);
            System.out.println("Decrypted: " + pair[0] + ": " + decrypt(pair[1]));
        }


    }

    private static String encrypt(byte[] property) throws GeneralSecurityException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));

        //Encrypt and save to temporary storage.
        String encrypted = Base64.getEncoder().encodeToString(pbeCipher.doFinal(property));

        //Cleanup data-sources - Leave no traces behind.
        Arrays.fill(property, (byte) 0);
        property = null;
        System.gc();

        //Return encryption result.
        return encrypted;
    }

    private static String encrypt(char[] property) throws GeneralSecurityException {
        //Prepare and encrypt.
        byte[] bytes = new byte[property.length];
        for (int i = 0; i < property.length; i++) {
            bytes[i] = (byte) property[i];
        }
        String encrypted = encrypt(bytes);

        /*
         * Cleanup property here. (child data-source 'bytes' is cleaned inside 'encrypt(byte[])').
         * It's not being done because the sources are being used multiple times for the different layer samples.
         */
        //cleanup allocated data.
        Arrays.fill(property, (char) 0);
        property = null; //de-allocate data (set for GC).
        System.gc(); //Attempt triggering garbage-collection.

        return encrypted;
    }

    private static String encrypt(String property) throws GeneralSecurityException {
        String encrypted = encrypt(property.getBytes());
        /*
         * Strings can't really have their allocated data cleaned before CG,
         * that's why secure data should be handled with char[] or byte[].
         * Still, don't forget to set for GC, even for data of sesser importancy;
         * You are making everything safer still, and freeing up memory as bonus.
         */
        property = null;
        return encrypted;
    }

    private static String decrypt(String property) throws GeneralSecurityException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        byte[] decoded = Base64.getDecoder().decode(property);
        byte[] finalDecoded = pbeCipher.doFinal(decoded);
        return new String(finalDecoded);
    }

    private static void create_EncryptedFile(
            String fileName,
            Map<String, String> commonAttributes,
            Map<String, char[]> secureAttributes,
            int layers)
            throws GeneralSecurityException, IOException {
        StringBuilder sb = new StringBuilder();
        for (String k : commonAttributes.keySet()) {
            sb.append(k).append(": ").append(commonAttributes.get(k)).append(System.lineSeparator());
        }
        //Encrypts secure attribute values only.
        for (String k : secureAttributes.keySet()) {
            String encryptedValue;
            if (layers >= 1) {
                encryptedValue = encrypt(secureAttributes.get(k));
            } else {
                encryptedValue = new String(secureAttributes.get(k));
            }
            sb.append(k).append(": ").append(encryptedValue).append(System.lineSeparator());
        }

        //Prepare file and file-writing process.
        File f = new File(RESOURCE_DIR, fileName);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        } else if (f.exists()) {
            f.delete();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.append(sb.toString().trim());
        bw.flush();
        bw.close();
    }

    private static String readFile_NoDecryption(String fileName) throws  IOException {
        File f = new File(RESOURCE_DIR, fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();
        while (br.ready()) {
            sb.append(br.readLine()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    private static String readFile_ApplyDecryption(String fileName) throws  IOException, GeneralSecurityException {
        File f = new File(RESOURCE_DIR, fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();
        while (br.ready()) {
            sb.append(br.readLine()).append(System.lineSeparator());
        }
        return decrypt(sb.toString());
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.exercice2.decryptage;

/**
 *
 * @author celo
 */

 import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.spec.SecretKeySpec;

public class StartDecryption {
    
    public PrivateKey getPrivate(String filename, String algorithm) throws Exception {

        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePrivate(spec);

    }

    public PublicKey getPublic(String filename, String algorithm) throws Exception {

        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePublic(spec);

    }

    public SecretKeySpec getSecretKey(String filename, String algorithm) throws IOException{

        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        return new SecretKeySpec(keyBytes, algorithm);

    }

    public static void main(String[] args) throws IOException, GeneralSecurityException, Exception{

        StartDecryption startEnc = new StartDecryption();

        File encryptedKeyReceived = new File("EncryptedFiles/encryptedSecretKey");
        File decreptedKeyFile = new File("DecryptedFiles/SecretKey");
        new DecryptKey(startEnc.getPrivate("KeyPair/privateKey_Bob", "RSA"),
                encryptedKeyReceived, decreptedKeyFile, "RSA");

        File encryptedFileReceived = new File("EncryptedFiles/encryptedFile");
        File decryptedFile = new File("DecryptedFiles/decryptedFile");
        new DecryptData(encryptedFileReceived, decryptedFile, 
                startEnc.getSecretKey("DecryptedFiles/SecretKey", "AES"), "AES");

        BufferedReader lireFichier = null;
        String ligne;

        try {
            lireFichier = new BufferedReader(new FileReader("DecryptedFiles/decryptedFile"));
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("Le fichier d??crypt?? est : ");
        while ((ligne = lireFichier.readLine()) != null) {
            System.out.println(ligne);
        }
        lireFichier.close();
    }
}
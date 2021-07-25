/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.exercice2.resultat;

/**
 *
 * @author celo
 */

 
import sn.ugb.ipsl.exercice2.cryptage.EncryptData;
import sn.ugb.ipsl.exercice2.cryptage.EncryptKey;

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
public class StartEncryption {
    
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

    public SecretKeySpec getSecretKey(String filename, String algorithm) throws IOException {

        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        return new SecretKeySpec(keyBytes, algorithm);

    }

    public static void main(String[] args)
            throws IOException, GeneralSecurityException, Exception {

        StartEncryption startEnc = new StartEncryption();

        File originalKeyFile = new File("OneKey/secretKey");
        File encryptedKeyFile = new File("EncryptedFiles/encryptedSecretKey");
        new EncryptKey(startEnc.getPublic("KeyPair/publicKey_Bob", "RSA"),
                originalKeyFile, encryptedKeyFile, "RSA");

        File originalFile = new File("Texte-en-clair.txt");
        File encryptedFile = new File("EncryptedFiles/encryptedFile");
        new EncryptData(originalFile, encryptedFile,
                startEnc.getSecretKey("OneKey/secretKey", "AES"), "AES");
        
        BufferedReader lireFichier = null,lireFichier1 = null,lireFichier2 = null;
        String ligne,ligne1,ligne2;
         try {
            lireFichier = new BufferedReader(new FileReader("Texte-en-clair.txt"));
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("\n");
        System.out.println("Le message en clair avant chiffrement est : ");
        while ((ligne = lireFichier.readLine()) != null) {
            System.out.println(ligne);
        }
        lireFichier.close();
        
        try {
            lireFichier1 = new BufferedReader(new FileReader("EncryptedFiles/encryptedSecretKey"));
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("\n");
        System.out.println("Le clé crypté est : ");
        while ((ligne1 = lireFichier1.readLine()) != null) {
            System.out.println(ligne1);
        }
        lireFichier1.close();
        System.out.println("\n");
        try {
            lireFichier2 = new BufferedReader(new FileReader("EncryptedFiles/encryptedFile"));
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }
        System.out.println("Le message en clair ou fichier après chiffrement est : ");
        while ((ligne2 = lireFichier2.readLine()) != null) {
            System.out.println(ligne2);
        }
        lireFichier2.close();
        
    }

}


    
    
    
   
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.exercice2.resultat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author celo
 */
public class Lecture {
    
    static int i=0;
    public static void main(String[] args) throws IOException{
       Scanner sc = new Scanner(System.in);
       System.out.println("Veuillez donner le nombre de ligne a saisir");  
       int ligne=sc.nextInt();
       System.out.println("Veuillez saisir le message à crypter");
       while(i<=ligne){
    
   
        
        String Message = sc.nextLine();
       
    
      
    File file =new File("Texte-en-clair.txt");
    BufferedWriter buff=new BufferedWriter(new FileWriter(file));
    
   
    buff.write(Message, 0, Message.length());
    buff.flush();
    i++;
   
    
       }  
    


    }
}
    

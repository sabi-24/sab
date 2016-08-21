package client;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Scanner;
import javax.crypto.*;
public class Client {
    public static void main(String[] args) throws IOException , SocketException, InvalidKeyException,IllegalBlockSizeException, NoSuchAlgorithmException, Exception{
        File f1=new File("C:\\Users\\RAFIQUE\\Documents\\NetBeansProjects\\Client\\src\\client\\input.txt");
//        File f1=new File("");
        FileReader fr=new FileReader(f1);
        BufferedReader br=new BufferedReader(fr);
        Socket s=new Socket("127.0.0.1",1000);
        
        DataInputStream sc1;
        sc1 = new DataInputStream(s.getInputStream());
        String no=br.readLine();
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        DesEncrypter  encrypter = new DesEncrypter (key);
        String encrypted = encrypter.encrypt(no);

        
        PrintStream p=new PrintStream(s.getOutputStream());
        p.println(encrypted);
        String temp;
        temp = sc1.readLine();
        String s3=encrypter.decrypt(temp);
while(true)
        {   int choice;
        Scanner scan=new Scanner(System.in);
            System.out.println("---MENU---");
            System.out.println("1. Send File");
            System.out.println("2. Receive File");
            System.out.println("3. Exit");
            System.out.print("\nEnter Choice :");
            
            choice=Integer.parseInt(scan.nextLine());
            if(choice==1)
            {
        File file2=new File("C:\\Users\\RAFIQUE\\Documents\\NetBeansProjects\\Client\\src\\client\\output.txt");
   //     File file2=new File("");
        FileWriter fw=new FileWriter(file2);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(s3);
        System.out.println("File sent to Server");
        bw.close();
                
            
            }
            else if(choice==2)
            {
                //DataOutputStream f;
        File file3=new File("C:\\Users\\RAFIQUE\\Documents\\NetBeansProjects\\Server\\src\\server\\encrypt_server.txt");
   //     File file2=new File("");
        String s4=encrypter.decrypt(temp);

   String temp1;
       // temp1 = sc1.readLine();
      File file4=new File("C:\\Users\\RAFIQUE\\Documents\\NetBeansProjects\\Client\\src\\client\\output.txt");

   FileWriter fw=new FileWriter(file4);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(s4);
        bw.close();
        System.out.println("File Received from Server");

            }
            else
            {
                System.exit(1);
            }
        
        
        }
    }
    
}

class DesEncrypter {
  Cipher ecipher;

  Cipher dcipher;

  DesEncrypter(SecretKey key) throws Exception {
    ecipher = Cipher.getInstance("DES");
    dcipher = Cipher.getInstance("DES");
    ecipher.init(Cipher.ENCRYPT_MODE, key);
    dcipher.init(Cipher.DECRYPT_MODE, key);
  }

  public String encrypt(String str) throws Exception {
    // Encode the string into bytes using utf-8
    byte[] utf8 = str.getBytes("UTF8");

    // Encrypt
    byte[] enc = ecipher.doFinal(utf8);

    // Encode bytes to base64 to get a string
    return new sun.misc.BASE64Encoder().encode(enc);
  }

  public String decrypt(String str) throws Exception {
    // Decode base64 to get bytes
    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

    byte[] utf8 = dcipher.doFinal(dec);

    // Decode using utf-8
    return new String(utf8, "UTF8");
  }
}

package server;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) throws IOException
    {
        ServerSocket s1=new ServerSocket(1000);
        System.out.println("Waiting for Connection from Client");
        Socket soc=s1.accept();
                System.out.println("Server Started on Port Number 1000");
        

        DataInputStream sc=new DataInputStream(soc.getInputStream());
             //   System.out.println(sc);

        String s=sc.readLine();
        File f3=new File("C:\\Users\\RAFIQUE\\Documents\\NetBeansProjects\\Server\\src\\server\\encrypt_server.txt");
//        File f3=new File("");
        FileWriter fw=new FileWriter(f3);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(s);
        bw.close();
        PrintStream p=new PrintStream(soc.getOutputStream());
     //   System.out.println(p);
        p.println(s);
        
        
    }
}
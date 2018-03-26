import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.net.SocketException;

public class Client{


    public static void main(String[] args) throws IOException{

        BufferedReader read = null;
        DataOutputStream write = null;
        Socket sock = null;

        try{
            sock = new Socket("localhost", 5644);
            System.out.println("Connected to "+sock.getInetAddress());
            read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            write = new DataOutputStream(sock.getOutputStream());

        }catch(UnknownHostException e){
            System.out.println("Host not found");
        }catch(IOException e){
            System.out.println("Error connecting to host");
            System.exit(2);
        }

        System.out.println("Start texting now: ");
        Scanner console = new Scanner(System.in);

        try{
            while(true){
                System.out.println("> ");
                String line = console.nextLine();
                line += "\r\n";
                write.writeBytes(line);
                if (line.equalsIgnoreCase("quit")){
                    System.out.println("Exited.");
                    break;
                }

                //wait for server's response
                line = read.readLine();
                System.out.println("Server: "+line);

            }
        }catch(SocketException e){
            System.out.println("Connection lost.");
        }catch(IOException e){
            System.out.println("IOException");
        }finally{
            read.close();
            write.close();
            sock.close();
        }
    }
}

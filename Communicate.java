import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

public class Communicate{
    public static void main(String [] args) throws IOException{
        try{
            Socket server = new Socket("192.168.1.159", 8080);
            InputStream in = server.getInputStream();
            OutputStream out = server.getOutputStream();

            //write a byte
            out.write(42);

            //write a newline or carriage return delimited string
            PrintWriter pout = new PrintWriter(out, true);
            pout.println("Hey biraj");

            //read a byte
            byte back = (byte)in.read();

            //read a newline or carriage return delimited string
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            String response = bin.readLine();

            //send a serialized Java object
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(new java.util.Date());
            oout.flush();

            server.close();
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
    }
}
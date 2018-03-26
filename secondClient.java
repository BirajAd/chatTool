import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.SocketException;

public class secondClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        BufferedReader read = null;
        DataOutputStream write = null;

        try {
            socket = new Socket("192.168.1.159", 56412);
            System.out.println("connected to " + socket.getInetAddress());
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write = new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException e) {
            System.out.println("Couldn't find the Host");
        } catch (IOException e) {
            System.out.println("IOException");
            System.exit(2);
        }

        System.out.println("time to chat: ");
        Scanner box = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("> ");
                String line = box.nextLine();
                line += "\r\n";
                write.writeBytes(line);
                if (line.equalsIgnoreCase("quit")) {
                    System.out.println("logged out");
                    break;
                }

                line = read.readLine();
                System.out.println("From Server => " + line);

            }
        } catch (SocketException e) {
            System.out.println("Connection lost.");
        } catch (IOException e) {
            System.out.println("IOException");
        } finally {
            read.close();
            write.close();
            socket.close();
        }

    }
}

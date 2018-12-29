package socketTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Cain
 * @Description
 * @date 2018/11/8
 */
public class Server implements Runnable{

    private ServerSocket serverSocket;

    public Server(int port){
        try {
            serverSocket = new ServerSocket(8080);
            serverSocket.setSoTimeout(100000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Server waiting...");
                Socket serverSocket = this.serverSocket.accept();
                ObjectInputStream inputStream = new ObjectInputStream(serverSocket.getInputStream());
                //service
                String serviceName = inputStream.readUTF();
                //method
                String methodName = inputStream.readUTF();
                //paramTypeName
                Class[] paramTypes = (Class[]) inputStream.readObject();
                //param
                Object[] params = (Object[]) inputStream.readObject();

                Class serviceClass = Class.forName(serviceName);
                Method method = serviceClass.getMethod(methodName,paramTypes);
                Student student = (Student) method.invoke(serviceClass.newInstance(), params);

                ObjectOutputStream outputStream = new ObjectOutputStream(serverSocket.getOutputStream());
                outputStream.writeObject(student);

                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        new Thread(server).start();
    }
}

package socketTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author Cain
 * @Description
 * @date 2018/11/8
 */
public class Client<T> {

    private Socket clientSocket;

    public Client(){
        try {
            clientSocket = new Socket("127.0.0.1", 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T serviceProxy(Class serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), serviceClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ObjectOutputStream out = null;
                ObjectInputStream inputStream = null;
                try {
                    //注意new ObjectOutputStream、new ObjectInputStream的顺序
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeUTF(serviceClass.getName());
                    out.writeUTF(method.getName());
                    out.writeObject(method.getParameterTypes());
                    out.writeObject(args);
                    out.flush();
                    inputStream = new ObjectInputStream(clientSocket.getInputStream());
                    return (Student) inputStream.readObject();
                } finally {
                    if(clientSocket != null){
                        clientSocket.close();
                    }
                    if(out != null){
                        out.close();
                    }
                    if(inputStream != null){
                        inputStream.close();
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Client<StudentService> client = new Client<>();
        StudentService studentService = client.serviceProxy(StudentServiceImpl.class);
        System.out.println(studentService.getStudent("wuqiong").toString());

    }
}

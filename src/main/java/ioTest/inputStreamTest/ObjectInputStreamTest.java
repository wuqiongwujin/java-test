package ioTest.inputStreamTest;

import java.io.*;

/**
 * @author Cain
 * @Description
 * @date 2018/11/8
 */
public class ObjectInputStreamTest {
    public static void main(String[] args) {
        writeUTF();
        readUTF();
    }

    public static void writeUTF(){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("/Users/wuqiong/workspace/test.txt")));
            outputStream.writeUTF("I am ok");
            //flush在文件尾写入文件结束标志，不flush的话，readeUTF会抛EOFException
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readUTF(){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("/Users/wuqiong/workspace/test.txt")));
            System.out.println(inputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

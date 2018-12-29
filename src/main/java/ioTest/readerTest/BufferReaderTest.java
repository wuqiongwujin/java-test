package ioTest.readerTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Cain
 * @Description
 * @date 2018/11/8
 */
public class BufferReaderTest {
    public static void main(String[] args) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader("/Users/wuqiong/workspace/test.txt"));
            String line = "";
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
            System.out.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

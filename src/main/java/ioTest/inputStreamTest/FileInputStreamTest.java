package ioTest.inputStreamTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cain
 * @Description
 * @date 2018/11/6
 */
public class FileInputStreamTest {
    public static void main(String[] args) {
        File file = new File("/Users/wuqiong/workspace/test.txt");
        try {
            FileInputStream in = new FileInputStream(file);
            StringBuilder stringBuilder = new StringBuilder();
            int temp = 0;
            while((temp = in.read()) != -1){
                char word = (char)temp;
                stringBuilder.append(word);
            }
            String[] words = stringBuilder.toString().split(" ");
            Map<String, Integer> map = new HashMap<String, Integer>();
            for(String word : words){
                if(!map.keySet().contains(word)){
                    map.put(word, 0);
                }else{
                    int num = map.get(word);
                    map.put(word, ++num);
                }
            }
            int max = 0;
            String tarWord = "";
            for(Map.Entry<String,Integer> entry : map.entrySet()){
                String word = entry.getKey();
                int num = entry.getValue();
                if(max < num){
                    max = num;
                    tarWord = word;
                }
            }
            System.out.println(tarWord);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

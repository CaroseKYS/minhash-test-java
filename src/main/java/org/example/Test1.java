package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间:  </p>
 *
 * @author kangys
 * @version v1.0
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        List<String> originalLines = new ArrayList<>();
        Calculator calculator = new Calculator();

        try(InputStream is = new FileInputStream("../minhash-python-test/01.data.txt")){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String str;
            while((str = bufferedReader.readLine()) != null) {
                originalLines.add(str);
            }

            bufferedReader.close();
        }catch (IOException e) {
            //这里处理FileNoFoundExceotion等异常
            e.printStackTrace();
        }

        int len = originalLines.size();
        long start = System.currentTimeMillis();
        float[][] result = new float[len][len];
        for (int i = 0; i < len; i++) {
            System.out.println(i + " = " + i + ", time consuming [" + (System.currentTimeMillis() - start) / 1000 + "] seconds");
            for (int j = 0; j < len; j++) {
                result[i][j] = calculator.jaccard(originalLines.get(i), originalLines.get(j));
            }
        }
        System.out.println("time consuming: " + (System.currentTimeMillis() - start) / 1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(result[0][i]);
        }
    }
}

package program;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class Textbt2 {
    String text="";
    public void Text() {
        try {
            //读入TXT文件
            String pathname = "Text2.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                line=line+"\n";
                text+=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  
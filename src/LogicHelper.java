import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shelton on 2017/10/26.
 */
public class LogicHelper {

    DataHelper dataHelper;
    private char pro[];
    int p = 0, syn = 0;

    public LogicHelper(){
        dataHelper = new DataHelper();
        pro = new char[500];
        for(int i=0;i<500;i++){
            pro[i] = ' ';
        }
        initPro();
    }
    //for test
    public char[] getPro(){
        return pro;
    }

    private void initPro(){

        try {
            ArrayList<String> proString = dataHelper.readFile("Input.txt");
            int list_len = proString.size();
            for(int i=0;i<list_len;i++){
                String word = proString.get(i);
                int word_len = word.length();
                for(int j=0;j<word_len;j++){
                    pro[p++] = word.charAt(j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

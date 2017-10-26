import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shelton on 2017/10/26.
 */
public class LogicHelper {

    DataHelper dataHelper;
    private char pro[],token[];//pro数组持有程序，token用于暂存标识符
    private int p = 0, syn = 0, pro_len=0,m;//p是遍历指针，syn持有状态值，pro_len是程序的长度

    public LogicHelper(){
        dataHelper = new DataHelper();
        token = new char[50];
        initToken();
        initPro();
    }


    private void initPro(){
        try {
            ArrayList<String> pro_String_list= dataHelper.readFile("input/Input.txt");
            int list_len = pro_String_list.size();
            String pro_string = "";
            for(int i=0;i<list_len;i++){
                pro_string += pro_String_list.get(i);
            }
            pro = pro_string.toCharArray();
            pro_len = pro.length;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initToken(){
        for(int i=0;i<50;i++){
            token[i]=' ';
        }
    }

    private String charToString(char[] charArray){
        String result = "";
        for(int i=0;i < charArray.length;i++){
            if(charArray[i]!=' '){
                result += String.valueOf(charArray[i]);
            }
        }
        return result;
    }

    private void scanner(){
        initToken();
        char ch;
        ch = pro[p++];
        while((ch == ' ')||(ch == '\n')||(ch == '\t')){
            ch = pro[p++];
        }
        if((ch >= 'a' && ch <= 'z')||(ch >= 'A' && ch <= 'Z')){//判断标识符
            m=0;
            while((ch >= 'a' && ch<='z')||(ch >= 'A' && ch <= 'Z')||(ch >='0' && ch <= '9')){
                token[m++] = ch;
                if(p == pro_len){
                    break;
                }
                ch = pro[p++];
            }
            p--;
            String candidate = charToString(token);
            syn = 1;
            int tag = dataHelper.isKeyWord(candidate);//判断关键字
            if(tag > 0){
                syn = tag;
            }
        }
        else if(ch >= '0' && ch <= '9'){//判断数字
            m = 0;
            while(ch >= '0' && ch <= '9'){
                token[m++] = ch;
                if(p == pro_len){
                    break;
                }
                ch = pro[p++];
            }
            if(ch == '.'){//double
                token[m++] = ch;
                ch = pro[p++];
                while(ch >= '0' && ch <= '9'){
                    if(p == pro_len){
                        break;
                    }
                    token[m++] = ch;
                    ch = pro[p++];
                }
                p--;
                syn = 2;//double
            }else{
                p--;
                syn =3;//int
            }
        }
        else{
            if(p == pro_len && dataHelper.isToken(ch) == -1){
                syn = -1;//如果程序结尾不是界符的话，就报错。
                return;
            }
            m = 0;
            int tag = dataHelper.isToken(ch);
            if(tag > 0){
                syn = tag;
                token[m++] = ch;
                if(p!=pro_len){
                    ch = pro[p++];
                    int tag1 = dataHelper.transState(tag,ch);//双目操作符状态转换
                    if(tag1 > 0){
                        syn = tag1;
                        token[m++] = ch;
                    }else{
                        p--;
                    }
                }
            }else{
                syn = -1;
            }
        }
    }

    public void run() throws IOException {
        File resultFile = new File("result/Output.txt");
        FileWriter fileWriter = new FileWriter(resultFile);
        while(p!=pro_len){
            scanner();
            if(syn >0) {
                String s = "<" + Integer.toString(syn) + "," + charToString(token) + ">"+"\n";
                fileWriter.write(s);
                System.out.print(s);
            }else{
                fileWriter.write("error\n");
                System.out.println("error");
            }
        }
        fileWriter.close();
    }

    //======for test=========
    public char[] getPro(){
        return pro;
    }
    public int getPro_len(){
        return pro_len;
    }

}

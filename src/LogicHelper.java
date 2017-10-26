import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shelton on 2017/10/26.
 */
public class LogicHelper {

    DataHelper dataHelper;
    private char pro[],token[];
    private int p = 0, syn = 0, pro_len=0,m;

    public LogicHelper(){
        dataHelper = new DataHelper();
        token = new char[50];
        initToken();
        initPro();
    }


    private void initPro(){
        try {
            ArrayList<String> pro_String_list= dataHelper.readFile("Input.txt");
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
        if((ch >= 'a' && ch<='z')||(ch >= 'A' && ch <= 'Z')){
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
            int tag = dataHelper.isKeyWord(candidate);
            if(tag > 0){
                syn = tag;
            }
        }
        else if(ch >= '0' && ch <= '9'){
            m = 0;
            while(ch >= '0' && ch <= '9'){
                token[m++] = ch;
                if(p == pro_len){
                    break;
                }
                ch = pro[p++];
            }
            if(ch == '.'){
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

    }

    public void run(){
        while(p!=pro_len){
            scanner();
            if(syn >3) {
                System.out.println(syn);
                System.out.println(charToString(token));
            }else if(syn == 3){
                System.out.println(syn);
                System.out.println(charToString(token));
            }else if(syn == 2){
                System.out.println(syn);
                System.out.println(charToString(token));
            }else if(syn == 1){
                System.out.println(syn);
                System.out.println(charToString(token));
            }
        }
    }

    //======for test=========
    public char[] getPro(){
        return pro;
    }
    public int getPro_len(){
        return pro_len;
    }






}

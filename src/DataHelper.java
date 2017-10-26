import java.io.*;
import java.util.ArrayList;

/**
 * Created by shelton on 2017/10/25.
 */
public class DataHelper {
    public String initState[][];
    public String keyworkState[][];
    public String transState[][];

    private String [] txturl = {"InitState.txt","KeyWordState.txt","TransState.txt"};

    public DataHelper(){
        setInitState();
        setKeyworkState();
        setTransState();
    }

    private void setInitState(){
        ArrayList<String> datalist;
        try {
            datalist = readFile(txturl[0]);
            int data_len = datalist.size();
            initState = new String[data_len][2];
            for(int i=0;i<data_len;i++){
                String string_line = datalist.get(i);
                String datas_line[] = string_line.split(" ");
                for (int j=0;j<2;j++){
                    initState[i][j] = datas_line[j];
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    private void setKeyworkState(){
        ArrayList<String> datalist;
        try {
            datalist = readFile(txturl[1]);
            int data_len = datalist.size();
            keyworkState = new String[data_len][2];
            for(int i=0;i<data_len;i++){
                String string_line = datalist.get(i);
                String datas_line[] = string_line.split(" ");
                for(int j=0;j<2;j++){
                    keyworkState[i][j] = datas_line[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setTransState(){
        ArrayList<String> datalist;
        try {
            datalist = readFile(txturl[2]);
            int data_len = datalist.size();
            transState = new String[data_len][3];
            for(int i=0;i<data_len;i++){
                String string_line = datalist.get(i);
                String datas_line[] = string_line.split(" ");
                for (int j=0;j<3;j++){
                    transState[i][j] = datas_line[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> readFile(String url) throws IOException {

        ArrayList<String> datalist = new ArrayList<>();

        File file = new File(url);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String readline = "";

        while ((readline = bufferedReader.readLine())!=null){
            datalist.add(readline);
        }

        bufferedReader.close();
        fileReader.close();

        return datalist;

    }

    public int isKeyWord(String s){
        int keyword_nums = keyworkState.length;
        for(int i=0;i<keyword_nums;i++){
            if(s.equals(keyworkState[i][0])){
                return Integer.parseInt(keyworkState[i][1]);
            }
        }
        return -1;
    }

    public int isToken(char c){
        int initState_nums = initState.length;
        for(int i=0;i<initState_nums;i++){
            char token = initState[i][0].charAt(0);
            if(c == token){
                return Integer.parseInt(initState[i][1]);
            }
        }
        return -1;
    }

    public int transState(int state, char ch){
        int transState_nums = transState.length;
        for(int i=0;i<transState_nums;i++){
            int oldstate = Integer.parseInt(transState[i][0]);
            char nowchar = transState[i][1].charAt(0);
            if(oldstate == state && nowchar == ch){
                int nowState = Integer.parseInt(transState[i][2]);
                return nowState;
            }
        }
        return -1;
    }

    public boolean isTerminal(int tag){
        int initState_nums = initState.length;
        for(int i=0;i<initState_nums;i++){
            if(tag == Integer.parseInt(initState[i][1])){
                return true;
            }
        }
        return false;
    }



}

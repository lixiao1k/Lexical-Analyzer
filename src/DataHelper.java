import java.io.*;
import java.util.ArrayList;

/**
 * Created by shelton on 2017/10/25.
 */
public class DataHelper {
    public String initState[][];
    public String keyworkState[][];
    public String transState[][];

    private String [] txturl = {"dataSource/InitState.txt","dataSource/KeyWordState.txt","dataSource/TransState.txt"};

    public DataHelper(){
        setState(StateStyle.INITSTATE);
        setState(StateStyle.KEYWORDSTATE);
        setState(StateStyle.TRANSSTATE);
    }

    private void setState(StateStyle stateStyle){
        ArrayList<String> datalist;
        try {

            datalist = readFile(txturl[stateStyle.ordinal()]);
            int data_len = datalist.size();

            if(stateStyle == StateStyle.INITSTATE){
                initState = new String[data_len][2];
                setStatelist(datalist,initState,data_len,2);

            }else if(stateStyle == StateStyle.KEYWORDSTATE){
                keyworkState = new String[data_len][2];
                setStatelist(datalist,keyworkState,data_len,2);

            }else if(stateStyle == StateStyle.TRANSSTATE){
                transState = new String[data_len][3];
                setStatelist(datalist,transState,data_len,3);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void setStatelist(ArrayList<String> data, String[][] list, int len1, int len2){
        for(int i=0;i<len1;i++){
            String string_line = data.get(i);
            String datas_line[] = string_line.split(" ");
            for(int j=0;j<len2;j++){
                list[i][j] = datas_line[j];
            }
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
}

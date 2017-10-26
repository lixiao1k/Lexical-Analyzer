import java.io.IOException;

/**
 * Created by shelton on 2017/10/26.
 */
public class Main {
    public static void main(String args []){
        LogicHelper logicHelper = new LogicHelper();
        try {
            logicHelper.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

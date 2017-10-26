import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by shelton on 2017/10/26.
 */
public class LogicHelperTest {
    LogicHelper logicHelper;
    @Before
    public void init(){
        logicHelper = new LogicHelper();
    }
    @Test
    public void testInit(){
        char pro[] = logicHelper.getPro();
        String s = String.valueOf(pro);
        for(int i = 0;i<logicHelper.getPro_len();i++){
            System.out.println(pro[i]);
        }
        System.out.println(s);
        System.out.println("pre_len: "+logicHelper.getPro_len());
        System.out.println();
    }


    @Test
    public void testRun(){
        try {
            logicHelper.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
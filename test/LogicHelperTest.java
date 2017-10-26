import org.junit.Before;
import org.junit.Test;

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
        int p = 0;
        for(int i=0;i<500;i++){
            System.out.print(pro[i]);
            p++;
        }
        System.out.println();
        System.out.println("P: "+ --p);
    }

}
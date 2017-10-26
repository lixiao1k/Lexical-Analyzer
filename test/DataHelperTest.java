import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by shelton on 2017/10/25.
 */

public class DataHelperTest {
    DataHelper dataHelper;
    @Before
    public void init(){
        dataHelper = new DataHelper();
    }
    @Test
    public void testdata(){
        System.out.println("这是initState.txt的数据============================");
        String[][] initState = dataHelper.initState;
        print(initState);
        System.out.println("这是keywordState.txt的数据=========================");
        String[][] keywordState = dataHelper.keyworkState;
        print(keywordState);
        System.out.println("这是TransState.txt的数据===========================");
        String[][] transState = dataHelper.transState;
        print(transState);

    }
    @Test
    public void testkeyword(){
        assertEquals(60,dataHelper.isKeyWord("if"));
        assertEquals(61,dataHelper.isKeyWord("else"));
        assertEquals(99,dataHelper.isKeyWord("private"));
        assertEquals(111,dataHelper.isKeyWord("volatile"));
        assertEquals(-1,dataHelper.isKeyWord("huh"));

    }

    @Test
    public void testToken(){
        assertEquals(10,dataHelper.isToken('+'));
        assertEquals(35,dataHelper.isToken('\"'));
        assertEquals(-1,dataHelper.isToken('0'));
    }

    @Test
    public void testTrans(){
        assertEquals(48,dataHelper.transState(24,'='));
        assertEquals(40,dataHelper.transState(10,'='));
        assertEquals(54,dataHelper.transState(16,'<'));
        assertEquals(44,dataHelper.transState(16,'='));
        assertEquals(-1,dataHelper.transState(5,'f'));
        assertEquals(-1,dataHelper.transState(10,'<'));
    }

    @Test
    public void testTerminal(){
        assertTrue(dataHelper.isTerminal(10));
        assertTrue(dataHelper.isTerminal(35));
        assertTrue(dataHelper.isTerminal(23));
        assertTrue(!dataHelper.isTerminal(44));
    }


    private void print(String[][] datalist){
        int len1 = datalist.length;
        int len2 = datalist[0].length;
        for(int i=0;i<len1;i++) {
            for (int j = 0; j < len2; j++) {
                System.out.print(datalist[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================================================");
    }
}
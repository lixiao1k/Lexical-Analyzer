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
        assertEquals(4,dataHelper.isKeyWord("abstract"));
        assertEquals(16,dataHelper.isKeyWord("else"));
        assertEquals(34,dataHelper.isKeyWord("private"));
        assertEquals(51,dataHelper.isKeyWord("while"));
        assertEquals(-1,dataHelper.isKeyWord("huh"));

    }

    @Test
    public void testToken(){
        assertEquals(52,dataHelper.isToken('+'));
        assertEquals(64,dataHelper.isToken('\"'));
        assertEquals(-1,dataHelper.isToken('0'));
    }

    @Test
    public void testTrans(){
        assertEquals(79,dataHelper.transState(52,'='));
        assertEquals(82,dataHelper.transState(55,'='));
        assertEquals(88,dataHelper.transState(62,'<'));
        assertEquals(92,dataHelper.transState(72,'='));
        assertEquals(-1,dataHelper.transState(5,'f'));
        assertEquals(-1,dataHelper.transState(10,'<'));
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
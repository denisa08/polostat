import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by d.aleksandrov on 30.12.2017.
 */
public class ArrTest {

    @Test
    public void set_test(){
        Set<String> testSet = new LinkedHashSet<>();
        testSet.add("test1");
        testSet.add("test1");
        testSet.add("test2");
        testSet.add("test3");
        testSet.add("test2");
        testSet.add("test4");
        testSet.add("test5");

        testSet.clear();

        System.out.println(testSet);








    }


}

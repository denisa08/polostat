import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by d.aleksandrov on 03.12.2017.
 */
public class CoffeineTest {

    @Test
    public void test1() throws InterruptedException {
        LoadingCache<String, String> booksByTitle = Caffeine.newBuilder()
                .maximumSize(10_000)
                .refreshAfterWrite(1000, TimeUnit.MILLISECONDS)
                .build(title -> { // Using a jOOQ repository


                    String uuid = UUID.randomUUID().toString();
                     Thread.sleep(5000);
                    return "uuid = " + uuid;
                 });


    Thread.sleep(1000);
        System.out.println("1"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("2"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("3"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("4"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("5"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("6"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("7"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("8"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("9"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("10"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("11"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("12"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("13"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("14"+booksByTitle.get(""));
        Thread.sleep(1000);
        System.out.println("15"+booksByTitle.get(""));










    }


}

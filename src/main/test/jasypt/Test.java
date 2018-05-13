package jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import ru.denisa.TestBoot;

import static org.jasypt.properties.PropertyValueEncryptionUtils.isEncryptedValue;

/**
 * Created by d.aleksandrov on 15.01.2018.
 */


public class Test {


    private static final String ENCRYPTED_VALUE_PREFIX = "ENC(";
    private static final String ENCRYPTED_VALUE_SUFFIX = ")";


    @org.junit.Test
    public void test1(){

        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword("dfgdfggSsdert");
        String myEncryptedText = textEncryptor.encrypt("trertrtyrtyrty");

        String plainText = textEncryptor.decrypt(myEncryptedText);

    }


}

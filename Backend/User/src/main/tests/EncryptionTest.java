package myProject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class EncryptionTest   {

    @Test
    public void testPasswordDecryption()   {
        //Create an instance of the encryption and test it using
        AESEncryptionDecryption tester1 = new AESEncryptionDecryption();
        String encryptedPassword = "w8ZEWJQRv4daapz97t6V/A==";

        //Check if it works by calling the method and checking the result
        assertEquals("password", tester1.decrypt(encryptedPassword, tester1.passwordKey));
    }

    @Test
    public void testEncryptionLettersAndNumbers()   {
        //Create an instance of the encryption and test it using
        AESEncryptionDecryption tester2 = new AESEncryptionDecryption();
        String openPassword = "password123";

        //Check if it works by calling the method and checking the result
        assertEquals("rrYZ1ndvuN5hzIvR1e/P3Q==", tester2.encrypt(openPassword, tester2.passwordKey));
    }

    @Test
    public void testEncryptionPasswordSpecialChar()   {
        //Create an instance of the encryption and test it using
        AESEncryptionDecryption tester3 = new AESEncryptionDecryption();
        String openPassword = "password!@#$";

        //Check if it works by calling the method and checking the result
        assertEquals("KSShCOH35dR3VyhAp0vY1Q==", tester3.encrypt(openPassword, tester3.passwordKey));
    }

    @Test
    public void testEncryptionPasswordBlank()   {
        //Create an instance of the encryption and test it using
        AESEncryptionDecryption tester4 = new AESEncryptionDecryption();
        String openPassword = "";

        //Check if it works by calling the method and checking the result
        assertEquals("MV42HzJVzC8fTaWuP1VzDQ==", tester4.encrypt(openPassword, tester4.passwordKey));
    }

    @Test
    public void demo4Test1()   {
        //Create an instance of the encryption and test it using
        AESEncryptionDecryption tester5 = new AESEncryptionDecryption();
        String openPassword = "demo4Test1";

        //Check if it works by calling the method and checking the result
        assertEquals("l143K436++5xOrGM21OrtA==", tester5.encrypt(openPassword, tester5.passwordKey));
    }

    @Test
    public void demo4Test2()   {
        //Create an instance of the encryption and test it using
        AESEncryptionDecryption tester6 = new AESEncryptionDecryption();
        String openPassword = "demo4Test2";

        //Check if it works by calling the method and checking the result
        assertEquals("qqkpOk1NyF1/SVThC209Eg==", tester6.encrypt(openPassword, tester6.passwordKey));
    }


}

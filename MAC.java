import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class MAC {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the message:");
        String sender= scan.nextLine();
        genMAC(sender);
    }
    public static void genMAC(String m){
        try {
            KeyGenerator KG = KeyGenerator.getInstance("AES");
            SecureRandom SR=new SecureRandom();/*used to generate a random number*/
            KG.init(SR);
            Key key=KG.generateKey(); /*The key generator generates the key for the specified algorithm*/
            Mac mac=Mac.getInstance("HmacSHA256");/*Returns a Mac object that implements the specified MAC algorithm.*/
            mac.init(key); /*Initialize the MAC object with the given key*/
            byte[] arr=m.getBytes(StandardCharsets.UTF_8);
            byte[] macVal=mac.doFinal();/*executes the mac proces*/
            System.out.println("Your MAC value at Sender's end: "+ macVal.toString());
            recMac(m,macVal,key,SR);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void recMac(String m,byte[] macVal,Key key,SecureRandom SR){
        try {
            Mac mac = Mac.getInstance("HmacSHA256");/*Returns a Mac object that implements the specified MAC algorithm.*/
            mac.init(key); /*Initialize the MAC object with the given key*/
            byte[] arr = m.getBytes(StandardCharsets.UTF_8);
            byte[] macValRec = mac.doFinal();
            System.out.println("Your MAC value at the Receiver's end: "+ macVal.toString());
            /*for(int j=0;j<macValRec.length;j++){
                System.out.println(macValRec[j]+" "+macVal[j]);
            }*/

            if(Arrays.equals(macValRec,macVal)){
                System.out.println("AUTHENTICATION PASSED");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

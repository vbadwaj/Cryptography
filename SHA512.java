import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

public class SHA512 {
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        System.out.println("Enter the value you want to find the hash value for");
        String message=scan.nextLine();
        String hash=findHash(message);
        System.out.println("The corresponding hash value is: "+hash);
    }
    public static String findHash(String Message){
        try {
            MessageDigest messdig = MessageDigest.getInstance("SHA-512");
            byte[] bytearr= messdig.digest(Message.getBytes(StandardCharsets.UTF_8));
            BigInteger integer=new BigInteger(1,bytearr);
            String hash=integer.toString(16);
            while(hash.length()<64){
                hash="0"+hash;
            }
            return hash;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
            }
}

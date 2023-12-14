///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.apache.commons:commons-lang3:3.12.0

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.security.SecureRandom;

public class pkce_generator {

    public static void main(String[] args) {
        
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[32];
        secureRandom.nextBytes(codeVerifier);
        
        String encodedCodeVerifier = Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);

        byte[] bytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(encodedCodeVerifier.getBytes());
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String codeChallenge = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

        System.out.println("Code Verifier: " + encodedCodeVerifier);
        System.out.println("Code Challenge: " + codeChallenge);
    }
}

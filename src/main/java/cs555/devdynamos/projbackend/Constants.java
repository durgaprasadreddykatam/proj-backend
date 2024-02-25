package cs555.devdynamos.projbackend;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class Constants {
    public static final String API_SECRET_KEY="UserserverapikeyThisIsASecretKeyForJWTTokenGeneration";
    public static final long TOKENVALIDITY=60*60*1000;
    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}

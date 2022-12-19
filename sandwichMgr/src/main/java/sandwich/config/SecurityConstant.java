package sandwich.config;

public class SecurityConstant {
    //public final static long EXPIRATION_TIME = 60000; // 1 minute en millisecondes
    //public final static long EXPIRATION_TIME = 3600000; // 1 heure en millisecondes
    public final static long EXPIRATION_TIME = 86400000; // 1 journée en millisecondes
    //public final static long EXPIRATION_TIME = 604800000; // 1 semaine en millisecondes
    public final static String JWT_KEY = "%AB4=$xrpsJy7k+$JDqs-YW8q9!T=2xn";
    /**
     * Exemple de Token :
     * Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
     */
    // !!! espace après le Bearer !!!
    public final static String TOKEN_PREFIX = "Bearer ";
    /**
     * HTTP HEADER
     * Authorization: Bearer TOKEN_VALUE
     */
    public final static String HEADER_KEY = "Authorization";
}

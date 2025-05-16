package project2;



import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class UrlShortener {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private HashMap<String, String> urlMap = new HashMap<>();
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    // Generate a random short URL using Base62 encoding
    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return shortUrl.toString();
    }

    // Shorten the given long URL
    public String shortenUrl(String longUrl) {
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (urlMap.containsKey(shortUrl));

        urlMap.put(shortUrl, longUrl);
        return "http://short.ly/" + shortUrl;
    }

    // Retrieve the original long URL
    public String getLongUrl(String shortUrl) {
        return urlMap.get(shortUrl.replace("http://short.ly/", ""));
    }

    // Dynamic input handling with Scanner
    public void run() {
        while (true) {
            System.out.println("\n1. Shorten a URL\n2. Retrieve a URL\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a long URL: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;
                case 2:
                    System.out.print("Enter the short URL: ");
                    String shortInput = scanner.nextLine();
                    String originalUrl = getLongUrl(shortInput);
                    System.out.println(originalUrl != null ? "Original URL: " + originalUrl : "URL not found!");
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        UrlShortener shortener = new UrlShortener();
        shortener.run();
    }
}

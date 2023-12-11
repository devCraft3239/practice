package code.mix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubdomainCount {
    public static Map<String, Integer> getSubdomainCount(Map<String, Integer> domainVisitCounts) {
        Map<String, Integer> subdomainCount = new HashMap<>();

        for (Map.Entry<String, Integer> entry : domainVisitCounts.entrySet()) {
            String domain = entry.getKey();
            int visitCount = entry.getValue();

            String[] subdomains = domain.split("\\.");

            // Iterate over subdomains and update their counts
            for (int i = 0; i < subdomains.length; i++) {
                StringBuilder subdomain = new StringBuilder();
                for (int j = i; j < subdomains.length; j++) {
                    if (j > i) {
                        subdomain.append(".");
                    }
                    subdomain.append(subdomains[j]);
                }
                subdomainCount.put(subdomain.toString(), subdomainCount.getOrDefault(subdomain.toString(), 0) + visitCount);
                System.out.println(subdomain+":"+subdomainCount.get(subdomain.toString()));
            }
        }

        return subdomainCount;
    }

    public static void main(String[] args) {
        // Sample data: domainVisitCounts map with domain as key and visit count as value
        Map<String, Integer> domainVisitCounts = new HashMap<>();
        domainVisitCounts.put("example.com", 10);
        domainVisitCounts.put("sub.example.com", 5);
        domainVisitCounts.put("sub.sub.example.com", 3);

        Map<String, Integer> subdomainCount = getSubdomainCount(domainVisitCounts);

        // Display the result
        for (Map.Entry<String, Integer> entry : subdomainCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
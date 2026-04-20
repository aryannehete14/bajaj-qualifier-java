package a.n.bajaj.service;

import a.n.bajaj.model.WebhookRequest;
import a.n.bajaj.model.WebhookResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        RestTemplate restTemplate = new RestTemplate();

        // STEP 1: CALL GENERATE WEBHOOK API [cite: 8, 9]
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        WebhookRequest request = new WebhookRequest();
        request.setName("Aryan Atul Nehete");
        request.setRegNo("ADT23SOCB0202");
        request.setEmail("aryannehete14@gmail.com");

        try {
            ResponseEntity<WebhookResponse> response =
                    restTemplate.postForEntity(url, request, WebhookResponse.class);

            WebhookResponse body = response.getBody();
            if (body != null) {
                String webhookUrl = body.getWebhook(); // [cite: 17]
                String token = body.getAccessToken(); // [cite: 18]

                System.out.println("Webhook URL: " + webhookUrl);
                System.out.println("Token: " + token);

                // STEP 2: SOLVE SQL [cite: 19, 22, 79]
                String finalQuery = solveSQL(request.getRegNo());

                // STEP 3: SEND ANSWER [cite: 24, 25]
                sendResult(webhookUrl, token, finalQuery);
            }
        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
        }
    }

    private String solveSQL(String regNo) {
        // Question 2: Group by department and count younger employees [cite: 81, 82]
        // Note: In SQL, DOB > current_DOB means the person is younger [cite: 65, 89]
        return "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, " +
                "(SELECT COUNT(*) FROM EMPLOYEE e2 WHERE e2.DEPARTMENT = e1.DEPARTMENT " +
                "AND e2.DOB > e1.DOB) AS YOUNGER_EMPLOYEES_COUNT " +
                "FROM EMPLOYEE e1 " +
                "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
                "ORDER BY e1.EMP_ID DESC;"; // [cite: 90]
    }

    private void sendResult(String url, String token, String query) {
        RestTemplate restTemplate = new RestTemplate();

        // [cite: 26, 27, 28]
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Using a Map ensures the JSON structure is valid
        Map<String, String> payload = new HashMap<>();
        payload.put("finalQuery", query);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> result = restTemplate.postForEntity(url, entity, String.class);
            System.out.println("✅ Answer Submitted! Response: " + result.getBody());
        } catch (Exception e) {
            System.err.println("❌ Submission Failed: " + e.getMessage());
        }
    }
}
package Arunmuthu_keySprint.backend_keySprint.RestController;

import Arunmuthu_keySprint.backend_keySprint.Entity.Score;
import Arunmuthu_keySprint.backend_keySprint.config.JwtUtil;
import Arunmuthu_keySprint.backend_keySprint.dao.ScoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/evaluate")
public class EvaluationController {

    private final ScoreRepository scoreRepo;
    private final JwtUtil jwtUtil;

    public EvaluationController(ScoreRepository scoreRepo, JwtUtil jwtUtil) {
        this.scoreRepo = scoreRepo;
        this.jwtUtil = jwtUtil;
    }

    // ---------- RESPONSE DTO ----------
    static class EvaluationResponse {
        public int score;
        public String feedback;
    }

    private static final String OLLAMA_URL =
            "http://localhost:11434/api/generate";

    @PostMapping
    public EvaluationResponse evaluate(
            @RequestBody Map<String, String> req,
            HttpServletRequest request
    ) {

        // ✅ Extract JWT manually
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing Authorization header");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        System.out.println("JWT USER → " + username);

        String scenario = req.get("scenario");
        String question = req.get("question");
        String answer = req.get("answer");

        // 🧠 Prompt
        String prompt = """
You are an evaluator.

Scenario:
%s

Question:
%s

User Answer:
%s

Return ONLY JSON:
{
  "score": <0-10>,
  "feedback": "<short feedback>"
}
""".formatted(scenario, question, answer);

        // 🤖 Ollama call
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = Map.of(
                "model", "phi3:mini",
                "prompt", prompt,
                "stream", false
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        OLLAMA_URL, entity, Map.class
                );

        String llmOutput =
                response.getBody().get("response").toString();

        // 🧪 Parse JSON safely
        String cleanJson = extractJson(llmOutput);

        ObjectMapper mapper = new ObjectMapper();
        EvaluationResponse eval;
        try {
            eval = mapper.readValue(cleanJson, EvaluationResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON parse failed: " + cleanJson);
        }

        // 🧯 Clamp score
        eval.score = Math.max(0, Math.min(10, eval.score));

        // 🏆 Update high score (DAO untouched)
        Score score = scoreRepo.findByUsername(username);
        if (score != null && eval.score > score.getScore()) {
            score.setScore(eval.score);
            scoreRepo.save(score);
        }

        return eval;
    }

    // ---------- JSON EXTRACTOR ----------
    private String extractJson(String text) {
        int start = text.indexOf('{');
        int end = text.lastIndexOf('}');
        if (start == -1 || end == -1 || start > end) {
            throw new RuntimeException(
                    "No valid JSON found in LLM output"
            );
        }
        return text.substring(start, end + 1);
    }
}

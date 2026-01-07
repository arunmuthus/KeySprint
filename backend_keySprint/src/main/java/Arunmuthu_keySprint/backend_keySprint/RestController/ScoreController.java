package Arunmuthu_keySprint.backend_keySprint.RestController;

import Arunmuthu_keySprint.backend_keySprint.Entity.Score;
import Arunmuthu_keySprint.backend_keySprint.config.JwtUtil;
import Arunmuthu_keySprint.backend_keySprint.dao.ScoreRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @Autowired
    ScoreRepository scoreRepo;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/score")
    public int getScore(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        Score score = scoreRepo.findByUsername(username);

        return score != null ? score.getScore() : 0;
    }

}

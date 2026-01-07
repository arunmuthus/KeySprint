package Arunmuthu_keySprint.backend_keySprint.RestController;

import Arunmuthu_keySprint.backend_keySprint.Entity.Score;
import Arunmuthu_keySprint.backend_keySprint.Entity.User;
import Arunmuthu_keySprint.backend_keySprint.config.JwtUtil;
import Arunmuthu_keySprint.backend_keySprint.dao.ScoreRepository;
import Arunmuthu_keySprint.backend_keySprint.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ScoreRepository scoreRepo;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (userRepo.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);

        Score score = new Score();
        score.setUsername(user.getUsername());
        score.setScore(0);

        scoreRepo.save(score);

        return "Registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = userRepo.findByUsername(user.getUsername());

        if (dbUser == null || !encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return "Invalid credentials";
        }

        return jwtUtil.generateToken(dbUser.getUsername());
    }
}

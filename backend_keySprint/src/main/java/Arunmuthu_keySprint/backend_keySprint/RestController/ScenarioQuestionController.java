package Arunmuthu_keySprint.backend_keySprint.RestController;

import Arunmuthu_keySprint.backend_keySprint.Entity.ScenarioQuestion;
import Arunmuthu_keySprint.backend_keySprint.dao.ScenarioQuestionDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/question")
public class ScenarioQuestionController {

    private final ScenarioQuestionDao dao;

    public ScenarioQuestionController(ScenarioQuestionDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<ScenarioQuestion> getQuestion() {
        return new ResponseEntity<>(dao.getRandomQuestion(), HttpStatus.OK);
    }
}

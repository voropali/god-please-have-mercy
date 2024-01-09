package cz.cvut.ear.clubevidence.rest;

import cz.cvut.ear.clubevidence.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/competitions")
public class CompetitionController {
    CompetitionService competitionService;
    @Autowired
    public CompetitionController(CompetitionService competitionService){
        this.competitionService = competitionService;
    }
}

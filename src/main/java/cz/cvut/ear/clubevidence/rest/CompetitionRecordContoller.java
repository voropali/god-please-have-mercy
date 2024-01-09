package cz.cvut.ear.clubevidence.rest;

import cz.cvut.ear.clubevidence.service.CompetitionRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/competition_records")
public class CompetitionRecordContoller {
    CompetitionRecordService competitionRecordService;
    public CompetitionRecordContoller(CompetitionRecordService competitionRecordService){
        this.competitionRecordService = competitionRecordService;
    }
}

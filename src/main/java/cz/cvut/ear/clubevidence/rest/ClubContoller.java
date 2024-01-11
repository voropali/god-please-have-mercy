package cz.cvut.ear.clubevidence.rest;

import cz.cvut.ear.clubevidence.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/clubs")
public class ClubContoller {
    ClubService clubService;
    @Autowired
    public ClubContoller(ClubService clubService){
        this.clubService = clubService;
    }

}

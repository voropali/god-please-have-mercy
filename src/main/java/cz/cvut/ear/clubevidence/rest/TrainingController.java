package cz.cvut.ear.clubevidence.rest;

import cz.cvut.ear.clubevidence.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/trainings")
public class TrainingController{
    TrainingService trainingService;
    @Autowired
    public TrainingController(){

}
}

package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.TrainingDao;
import cz.cvut.ear.clubevidence.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainingService {
    private final TrainingDao trainingDao;
    @Autowired
    public TrainingService(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }
    @Transactional(readOnly = true)
    public List<Training> findAll() {
        return trainingDao.findAll();
    }

}

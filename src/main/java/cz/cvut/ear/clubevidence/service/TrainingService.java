package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.TrainingDao;
import cz.cvut.ear.clubevidence.model.Training;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Transactional(readOnly = true)
    public Training find(Integer id) {
        return trainingDao.find(id);
    }

    @Transactional
    public void persist(Training training) {
        Objects.requireNonNull(training);
        trainingDao.persist(training);
    }

    @Transactional
    public void saveTraining(Training training) {
        Objects.requireNonNull(training);
        if(trainingDao.exists(training.getId())){
            throw new IllegalIdentifierException("Training with id: " + training.getId() + " already exists");
        }
        trainingDao.persist(training);
    }

}

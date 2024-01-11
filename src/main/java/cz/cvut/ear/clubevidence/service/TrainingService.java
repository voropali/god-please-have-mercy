package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.TrainingDao;
import cz.cvut.ear.clubevidence.dao.UserDao;
import cz.cvut.ear.clubevidence.exception.ExceptionGeneral;
import cz.cvut.ear.clubevidence.exception.NotFoundException;
import cz.cvut.ear.clubevidence.model.Training;
import cz.cvut.ear.clubevidence.model.User;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TrainingService {
    private final TrainingDao trainingDao;
    private final UserDao userDao;
    @Autowired
    public TrainingService(TrainingDao trainingDao, UserDao userDao) {
        this.trainingDao = trainingDao;
        this.userDao = userDao;
    }
    @Transactional
    public void persist(Training training) {
        Objects.requireNonNull(training);
        if(trainingDao.exists(training.getId())){
            throw new IllegalIdentifierException("Training with id: " + training.getId() + " already exists");
        }
        trainingDao.persist(training);
    }

    @Transactional(readOnly = true)
    public List<Training> findAll() {
        return trainingDao.findAll();
    }

    @Transactional(readOnly = true)
    public Training find(Integer id) {
        Objects.requireNonNull(id);
        if(!trainingDao.exists(id)){
            throw new NotFoundException("Training not found");
        }
        return trainingDao.find(id);
    }
    @Transactional
    public void update(Training training) {
        Objects.requireNonNull(training);
        if(!trainingDao.exists(training.getId())){
            throw new NotFoundException("Training not found");
        }
        trainingDao.update(training);
    }


    @Transactional
    public void remove(Training training) {
        Objects.requireNonNull(training);
        if(!trainingDao.exists(training.getId())){
            throw new NotFoundException("Training not found");
        }
        trainingDao.remove(training);
    }


    @Transactional
    public void registerUserForTraining(User member, Training training) {
        Objects.requireNonNull(training);
        Objects.requireNonNull(member);
        if(!userDao.exists(member.getId())){
            throw new NotFoundException("User not found");
        }

        if(!trainingDao.exists(training.getId())){
            throw new NotFoundException("Training not found");
        }

        if (training.getMembers().size() > 20) {
            throw new ExceptionGeneral("Number of participants may not exceed 20");
        }
        training.getMembers().add(member);

        trainingDao.update(training);
    }

    @Transactional
    public void unsubscribeFromTraining(User member, Training training) {
        Objects.requireNonNull(training);
        Objects.requireNonNull(member);
        if(!userDao.exists(member.getId())){
            throw new NotFoundException("User not found");
        }

        if(!trainingDao.exists(training.getId())){
            throw new NotFoundException("Competition not found");
        }

        training.getMembers().remove(member);

        trainingDao.update(training);
    }

}

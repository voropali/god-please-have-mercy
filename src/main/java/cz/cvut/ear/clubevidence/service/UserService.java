package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.CompetitionDao;
import cz.cvut.ear.clubevidence.dao.CompetitionRecordDao;
import cz.cvut.ear.clubevidence.dao.PaymentDao;
import cz.cvut.ear.clubevidence.dao.UserDao;
import cz.cvut.ear.clubevidence.exception.UserAlreadyExists;
import cz.cvut.ear.clubevidence.model.*;
import cz.cvut.ear.clubevidence.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserDao userDao;
    private final PaymentDao paymentDao;
    private  final CompetitionDao competitionDao;
    private  final CompetitionRecordDao competitionRecordDao;
    private  final CompetitionService competitionService;
    private final PasswordEncoder encoder;


    @Autowired
    public UserService(UserDao userDao, PaymentDao paymentDao, CompetitionDao competitionDao, CompetitionRecordDao competitionRecordDao, CompetitionService competitionService, PasswordEncoder encoder) {

        this.userDao = userDao;
        this.paymentDao = paymentDao;
        this.competitionDao = competitionDao;
        this.competitionRecordDao = competitionRecordDao;
        this.competitionService = competitionService;

        this.encoder = encoder;
    }

    @Transactional
    public void persist(User user) {
        Objects.requireNonNull(user);
        if(userDao.existsByUsername(user.getUsername())){
            throw UserAlreadyExists.create(user.getUsername());
        }
        user.encodePassword(encoder);
        if (user.getRole() == null) {
            user.setRole(Constants.DEFAULT_ROLE);
        }
        userDao.persist(user);
    }

    @Transactional(readOnly = true)
    public Object findById(Integer id) {
        Objects.requireNonNull(id);
        return userDao.find(id);
    }

    @Transactional(readOnly = true)
    public Object findByUsername(String username) {
        Objects.requireNonNull(username);
        return userDao.findByUsername(username);
    }


    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }



    @Transactional
    public void persistCompetition(User admin, Competition competition) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(competition);
        if(admin.getRole() == Role.ADMIN) {
            competitionDao.persist(competition);
        }
    }

    @Transactional
    public void persistCompetitionRecord(User admin, CompetitionRecord competitionRecord) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(competitionRecord);
        if(admin.getRole() == Role.ADMIN) {
            competitionRecordDao.persist(competitionRecord);
        }
    }


    @Transactional
    public void updateUser(User admin, User member) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(member);
        if(admin.getRole() == Role.ADMIN) {
            userDao.update(member);
        }
    }

    @Transactional
    public void updateCompetition(User admin, Competition competition) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(competition);
        if(admin.getRole() == Role.ADMIN) {
            competitionDao.update(competition);
        }
    }

    @Transactional
    public void updateCompetitionRecord(User admin, CompetitionRecord competitionRecord) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(competitionRecord);
        if(admin.getRole() == Role.ADMIN) {
            competitionRecordDao.update(competitionRecord);
        }
    }

    @Transactional
    public void removeUser(User admin, User member) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(member);
        if(admin.getRole() == Role.ADMIN) {
            userDao.remove(member);
        }
    }

    @Transactional
    public void updatePayment(User admin, Payment payment) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(payment);
        if(admin.getRole() == Role.ADMIN) {
            paymentDao.update(payment);
        }
    }

    @Transactional
    public void persistPayment(User admin, Payment payment) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(payment);
        if(admin.getRole() == Role.ADMIN) {
            paymentDao.persist(payment);
        }
    }

    @Transactional
    public void removeCompetition(User admin, Competition competition) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(competition);
        if(admin.getRole() == Role.ADMIN) {
            competitionDao.remove(competition);
        }
    }

    @Transactional
    public void removeCompetitionRecord(User admin, CompetitionRecord competitionRecord) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(competitionRecord);
        if(admin.getRole() == Role.ADMIN) {
            competitionRecordDao.remove(competitionRecord);
        }
    }

    @Transactional
    public void registerForCompetition(User member, Competition competition) {
        Objects.requireNonNull(member);
        Objects.requireNonNull(member);
        competitionService.registerUserForCompetition(member, competition);
    }


    @Transactional(readOnly = true)
    public boolean exists(String username) {
        return userDao.findByUsername(username) != null;
    }

}

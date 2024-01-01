package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.CompetitionDao;
import cz.cvut.ear.clubevidence.dao.CompetitionRecordDao;
import cz.cvut.ear.clubevidence.dao.PaymentDao;
import cz.cvut.ear.clubevidence.dao.UserDao;
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

    /**
     * Instantiates a new City service.
     *
     //* @param competitionDao the city dao
     */
    @Autowired
    public UserService(UserDao userDao, PaymentDao paymentDao, CompetitionDao competitionDao, CompetitionRecordDao competitionRecordDao, CompetitionService competitionService, PasswordEncoder encoder) {

        this.userDao = userDao;
        this.paymentDao = paymentDao;
        this.competitionDao = competitionDao;
        this.competitionRecordDao = competitionRecordDao;
        this.competitionService = competitionService;

        this.encoder = encoder;
    }

    /**
     * Find all page.
     *
     // * @param pageable the pageable
     // * @param name     the name
     * @return the page
     */
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * Find city.
     * <p>
     * //   * @param id the id
     *
     * @return the city
     */
    @Transactional(readOnly = true)
    public Object find(Integer id) {
        return userDao.find(id);
    }

    /**
     * Persist.
     *
     //   * @param city the city
     */
    @Transactional
    public void persist(User user) {
        Objects.requireNonNull(user);
        user.encodePassword(encoder);
        if (user.getRole() == null) {
            user.setRole(Constants.DEFAULT_ROLE);
        }
        userDao.persist(user);
    }

    @Transactional
    public void persistCompetition(User admin, Competition competition) {

        if(admin.getRole() == Role.ADMIN) {
            competitionDao.persist(competition);
        }
    }

    @Transactional
    public void persistCompetitionRecord(User admin, CompetitionRecord competitionRecord) {

        if(admin.getRole() == Role.ADMIN) {
            competitionRecordDao.persist(competitionRecord);
        }
    }

    /**
     * Update.
     *
     //   * @param city the city
     */
    @Transactional
    public void updateUser(User admin, User member) {
        userDao.update(member);
    }

    @Transactional
    public void updateCompetition(User admin, Competition competition) {

        if(admin.getRole() == Role.ADMIN) {
            competitionDao.update(competition);
        }
    }

    @Transactional
    public void updateCompetitionRecord(User admin, CompetitionRecord competitionRecord) {

        if(admin.getRole() == Role.ADMIN) {
            competitionRecordDao.update(competitionRecord);
        }
    }

    /**
     * Remove.
     *
     //    * @param city the city
     */
    @Transactional
    public void removeUser(User admin, User member) {
        if(admin.getRole() == Role.ADMIN) {
            userDao.remove(member);
        }
    }

    @Transactional
    public void updatePayment(User admin, Payment payment) {
        if(admin.getRole() == Role.ADMIN) {
            paymentDao.update(payment);
        }
    }

    @Transactional
    public void persistPayment(User admin, Payment payment) {
        if(admin.getRole() == Role.ADMIN) {
            paymentDao.persist(payment);
        }
    }

    @Transactional
    public void removeCompetition(User admin, Competition competition) {

        if(admin.getRole() == Role.ADMIN) {
            competitionDao.remove(competition);
        }
    }

    @Transactional
    public void removeCompetitionRecord(User admin, CompetitionRecord competitionRecord) {

        if(admin.getRole() == Role.ADMIN) {
            competitionRecordDao.remove(competitionRecord);
        }
    }

    @Transactional
    public void registerForCompetition(User member, Competition competition) {
        competitionService.registerUserForCompetition(member, competition);
    }


    @Transactional(readOnly = true)
    public boolean exists(String username) {
        return userDao.findByUsername(username) != null;
    }

}

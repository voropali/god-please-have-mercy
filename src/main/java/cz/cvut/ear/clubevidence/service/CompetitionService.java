package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.CompetitionDao;
import cz.cvut.ear.clubevidence.dao.UserDao;
import cz.cvut.ear.clubevidence.exception.NotFoundException;
import cz.cvut.ear.clubevidence.model.Competition;
import cz.cvut.ear.clubevidence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CompetitionService {
    private final CompetitionDao competitionDao;
    private final UserDao userDao;

    /**
     * Instantiates a new City service.
     *
     * @param competitionDao the city dao
     */
    @Autowired
    public CompetitionService(CompetitionDao competitionDao, UserDao userDao) {
        this.competitionDao = competitionDao;
        this.userDao = userDao;
    }

    /**
     * Find all page.
     *
     // * @param pageable the pageable
     // * @param name     the name
     * @return the page
     */
    @Transactional(readOnly = true)
    public List<Competition> getAllCompetitions() {
        return competitionDao.findAll();
    }

    /**
     * Find city.
     *
     * @param id the id
     * @return the city
     */
    @Transactional(readOnly = true)
    public Competition getCompetitionById(Integer id) {
        return competitionDao.find(id);
    }

    /**
     * Persist.
     *
     //   * @param city the city
     */
    @Transactional
    public void createCompetition(Competition competition) {
        competitionDao.persist(competition);
    }

    /**
     * Update.
     *
     //   * @param city the city
     */
    @Transactional
    public void updateCompetition(Competition competition) {
        competitionDao.update(competition);
    }

    /**
     * Remove.
     *
     //    * @param city the city
     */
    @Transactional
    public void deleteCompetition(Competition competition) {
        competitionDao.remove(competition);
    }

    @Transactional
    public void registerUserForCompetition(User member, Competition competition) {

        if (member == null) {
            throw new NotFoundException("User is not found.");
        }

        if (competition == null) {
            throw new NotFoundException("Competition is not found.");
        }

        if (competition.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDateTime.now().toLocalDate()) < 0) {
            throw new NotFoundException("Competition is not found.");
        }
        competition.getParticipants().add(member);

        competitionDao.update(competition);
    }
}

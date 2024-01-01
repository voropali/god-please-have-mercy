package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.CompetitionRecordDao;
import cz.cvut.ear.clubevidence.model.CompetitionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompetitionRecordService {
    private final CompetitionRecordDao competitionRecordDao;

    /**
     * Instantiates a new City service.
     *
     * @param competitionRecordDao the city dao
     */
    @Autowired
    public CompetitionRecordService(CompetitionRecordDao competitionRecordDao) {
        this.competitionRecordDao = competitionRecordDao;
    }

    /**
     * Find all page.
     *
     // * @param pageable the pageable
     // * @param name     the name
     * @return the page
     */
    @Transactional(readOnly = true)
    public List<CompetitionRecord> getAllCompetitionRecords() {
        return competitionRecordDao.findAll();
    }

    /**
     * Find city.
     *
     * @param id the id
     * @return the city
     */
    @Transactional(readOnly = true)
    public CompetitionRecord getCompetitionRecordById(Integer id) {
        return competitionRecordDao.find(id);
    }

    /**
     * Persist.
     *
     //    * @param city the city
     */
    @Transactional
    public void createCompetitionRecord(CompetitionRecord competitionRecord) {
        competitionRecordDao.persist(competitionRecord);
    }

    /**
     * Update.
     *
     //  * @param city the city
     */
    @Transactional
    public void updateCompetitionRecord(CompetitionRecord competitionRecord) {
        competitionRecordDao.update(competitionRecord);
    }

    /**
     * Remove.
     *
     // * @param city the city
     */
    @Transactional
    public void deleteCompetitionRecord(CompetitionRecord competitionRecord) {
        competitionRecordDao.remove(competitionRecord);
    }
}

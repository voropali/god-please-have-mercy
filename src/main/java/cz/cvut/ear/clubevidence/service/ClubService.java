package cz.cvut.ear.clubevidence.service;

import cz.cvut.ear.clubevidence.dao.ClubDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService {
    private final ClubDao clubDao;

    @Autowired
    public ClubService(ClubDao clubDao) {
        this.clubDao = clubDao;
    }
}

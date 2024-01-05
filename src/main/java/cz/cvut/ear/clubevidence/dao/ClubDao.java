package cz.cvut.ear.clubevidence.dao;

import cz.cvut.ear.clubevidence.model.Club;
import cz.cvut.ear.clubevidence.model.Competition;
import org.springframework.stereotype.Repository;

@Repository
public class ClubDao extends BaseDao<Club>{
    public ClubDao(){
        super(Club.class);
    }
}

package cz.cvut.ear.clubevidence.dao;

import cz.cvut.ear.clubevidence.model.Training;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDao extends BaseDao<Training>{
    public TrainingDao(){
        super(Training.class);
    }
}

package cz.cvut.ear.clubevidence.dao;

import cz.cvut.ear.clubevidence.model.Alert;
import org.springframework.stereotype.Repository;

@Repository
public class AlertDao extends BaseDao<Alert>{
    public AlertDao(){
        super(Alert.class);
    }
}

package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.Project;
import com.rest.api.marketplace.transports.DatastoreDoesnotExistException;
import com.rest.api.marketplace.transports.KeyDoesnotExistException;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectDao extends BaseDao<Project>{

    public static final String DATASTORE = "project";

    public List<Project> getList() throws DatastoreDoesnotExistException{
        LookupKey key = new LookupKey(DATASTORE, null);
        return list(key);
    }

    public Project get(String id) throws DatastoreDoesnotExistException, KeyDoesnotExistException {
        LookupKey key = new LookupKey(DATASTORE, id);
        return load(key);
    }

    public void create(Project projectObj){
        LookupKey key = new LookupKey(DATASTORE, projectObj.getId());
        store(key, projectObj);
    }

    public void edit(String id, Project projectObj){
        LookupKey key = new LookupKey(DATASTORE, id);
        update(key, projectObj);
    }
}

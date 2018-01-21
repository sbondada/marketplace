package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.Project;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

import java.util.List;

public class ProjectDao extends BaseDao<Project>{

    public static final String DATASTORE = "project";

    public List<Project> getList(){
        LookupKey key = new LookupKey(DATASTORE, null);
        return list(key);
    }

    public Project get(String id){
        LookupKey key = new LookupKey(DATASTORE, id);
        return load(key);
    }

}

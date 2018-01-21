package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.Project;

import java.util.List;

public class ProjectDao extends BaseDao<Project>{

    public static final String DATASTORE = "project";

    public List<Project> getList(){
        LookupKey key = new LookupKey(DATASTORE, null);
        return list(key);
    }

}

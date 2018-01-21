package com.rest.api.marketplace.daos;

import java.util.HashMap;

public class DaoRepository {

    private HashMap<String, Dao> daoMap;

    public Dao getDaoObject(Class<?> daoClass){
        String daoClassName = daoClass.getName();
        if (daoMap.containsKey((daoClassName))) {
            return daoMap.get(daoClassName);
        }
        else {
            return null;
        }
    }

    public void register(Class<?> daoClass) {
        String daoClassName = daoClass.getName();
        if (!daoMap.containsKey(daoClassName)) {
            try {
                Dao daoObject = (Dao) daoClass.newInstance();
                daoObject.init();
                daoMap.put(daoClassName, daoObject);
            }
            catch(InstantiationException e){
                //log the message
            }
            catch (IllegalAccessException e)
            {
                 //log the appropriate message
            }
        }
    }
}

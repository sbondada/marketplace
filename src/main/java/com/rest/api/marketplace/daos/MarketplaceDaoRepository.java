package com.rest.api.marketplace.daos;

import java.util.HashMap;

public class MarketplaceDaoRepository {

    private HashMap<String, Dao> daoMap;

    public MarketplaceDaoRepository(){
        daoMap = new HashMap<>();
    }

    public Dao getDaoObject(Class<?> daoClass){
        String daoClassName = daoClass.getName();
        if (daoMap.containsKey((daoClassName))) {
            return daoMap.get(daoClassName);
        }
        else {
            return null;
        }
    }

    public void register(Class<?> modelClass, Class<?> daoClass) {
        String modelClassName = modelClass.getName();
        if (!daoMap.containsKey(modelClassName)) {
            try {
                Dao daoObject = (Dao) daoClass.newInstance();
                daoObject.init();
                daoMap.put(modelClassName, daoObject);
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

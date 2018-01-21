package com.rest.api.marketplace.models;

import java.util.*;

public class LookupKey extends HashMap<String, String>{

    // This is used to identify the map or the collection or the table in the datastore
    public static final String DATASTORE = "datastore";
    // This is used to identify the item in the collection
    public static final String ID = "id";
    public static final String DEFAULT = "";
    public static final String ITEM_DELIMITER = ";";
    public static final String KEYVALUE_DELIMITER = "=";

    public LookupKey(){
        super();
        this.put(DATASTORE, DEFAULT);
        this.put(ID, DEFAULT);
    }

    public LookupKey(String datastore, String id){
        super();
        this.put(DATASTORE, datastore);
        this.put(ID, id);
    }

    public String getDatastore(){
        return this.get(DATASTORE);
    }

    public String getId(){
        return this.get(ID);
    }

    private String key;

    @Override
    public String toString(){
        if (key != null) {
            return key;
        }

        List<String> keyset = new ArrayList<String>(super.keySet());
        Collections.sort(keyset);
        StringBuffer sb = new StringBuffer();
        for (String mapKey : keyset) {
            sb.append(mapKey).append(KEYVALUE_DELIMITER).append(super.get(mapKey)).append(ITEM_DELIMITER);
        }

        key = sb.toString();
        return key;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof LookupKey))
            return false;

        if (toString().equals(((LookupKey)o).toString()))
            return true;
        else
            return false;
    }

    @Override
    public String put(String mapKey, String value) {
        if(value == null) {
            return remove(mapKey);
        }

        if(mapKey.indexOf('=') > -1 || mapKey.indexOf(';') > -1) {
            throw new IllegalArgumentException("LookupKey key can not contain '=' or ';'");
        }

        String valueStr = String.valueOf(value);
        if(valueStr.indexOf('=') > -1 || valueStr.indexOf(';') > -1) {
            throw new IllegalArgumentException("LookupKey value serialization can not contain '=' or ';'");
        }

        key = null;
        return super.put(mapKey, value);
    }

    public String remove(String mapKey) {
        key = null;
        return super.remove(mapKey);
    }

    public LookupKey copy() {
        LookupKey newCopy = new LookupKey();
        newCopy.clear();
        for (String o : this.keySet()) {
            newCopy.put(o, this.get(o));
        }
        return newCopy;
    }


}

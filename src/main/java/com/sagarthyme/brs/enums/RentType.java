package com.sagarthyme.brs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RentType {

    RENT,
    RETURN;

    /*private Integer key;
    private String status;

    public RentType getByKey() throws Exception{
        if (key == null) {
            throw new Exception("Key cannot be null");
        }
        RentType[] rentTypes = values();
        for (RentType rentType : rentTypes){
            if (key.equals(rentType.getKey())){
                return rentType;
            }
        }
        throw new Exception("Invalid status key");
    }*/
}

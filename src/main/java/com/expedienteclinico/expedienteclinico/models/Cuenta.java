package com.expedienteclinico.expedienteclinico.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter

public class Cuenta {

    private String persona;
    private BigDecimal saldo;

    public Cuenta(String persona, BigDecimal saldo){

        this.persona = persona;
        this.saldo = saldo;

    }

    @Override
    public boolean equals( Object object){

        if (! (object instanceof Cuenta)){

            return false;

        }

        Cuenta c = ( Cuenta) object;
        if (this.persona == null || this.saldo == null){
            return false;
        }

        return this.persona.equals(c.getPersona()) && this.saldo.equals( c.getSaldo());

    }

}

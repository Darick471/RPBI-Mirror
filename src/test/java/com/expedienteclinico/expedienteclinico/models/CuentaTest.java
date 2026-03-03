package com.expedienteclinico.expedienteclinico.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void testNombreCuenta(){

        Cuenta cuenta = new Cuenta("Alex", new BigDecimal("1500.471"));
        cuenta.setPersona("Alex");


        String esperado = "Alex";
        String real = cuenta.getPersona();

        assertEquals( esperado, real);
        assertTrue( real.equals( "Alex") );

    }

    @Test
    void testSaldoCuenta(){

        Cuenta cuenta = new Cuenta("Alex", new BigDecimal("1500.471"));
        assertEquals( 1500.471, cuenta.getSaldo().doubleValue() );
        assertFalse( cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0 );
        assertTrue( cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0 );

    }

    @Test
    void testReferenciaDeCuenta(){

        Cuenta cuenta = new Cuenta("Darikson", new BigDecimal("1471.471"));
        Cuenta cuenta2 = new Cuenta("Darikson", new BigDecimal("1471.471"));

        assertNotEquals( cuenta2 , cuenta ) ;

    }

}
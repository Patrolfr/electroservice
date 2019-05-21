package komo.fraczek.electronicsservice;


import komo.fraczek.electronicsservice.domain.ServiceStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusTest {


    @Test
    public void testParkingStatus(){
        assertEquals(ServiceStatus.valueOf("WORKING"), ServiceStatus.WORKING);
        assertEquals(ServiceStatus.valueOf("IN_SERVICE"), ServiceStatus.IN_SERVICE);
        assertEquals(ServiceStatus.valueOf("BROKEN"), ServiceStatus.BROKEN);
        assertEquals(ServiceStatus.valueOf("UNKNOWN"), ServiceStatus.UNKNOWN);

        assertEquals(ServiceStatus.WORKING.toString(),"WORKING");
        assertEquals(ServiceStatus.IN_SERVICE.toString(),"IN_SERVICE");
        assertEquals(ServiceStatus.UNKNOWN.toString(),"UNKNOWN");
    }
}

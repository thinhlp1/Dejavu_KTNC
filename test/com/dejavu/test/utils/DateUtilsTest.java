/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test.utils;

import com.bar.util.XDate;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADMIN
 */
public class DateUtilsTest {
    
    XDate dateUtils = new XDate();
    public DateUtilsTest() {
    }
    
    @Test
    public void testToDateFunction(){
        String dateStr = "12/08/2003";
        String format = "dd/MM/yyyy";
        Date dateExpected = XDate.toDate(dateStr, format);
        assertEquals(dateExpected,XDate.toDate(dateStr, format) );
    }
    
      @Test
    public void testToStringFunction(){
        String dateStr = "12/08/2003";
        String format = "dd/MM/yyyy";
        Date date = XDate.toDate(dateStr, format);
        assertEquals(dateStr ,XDate.toString(date, format));
    }
    
        @Test
    public void testAddDateFunction(){
        String dateStr = "12/08/2003";
        String format = "dd/MM/yyyy";
        Date date = XDate.toDate(dateStr, format);
        
        Date dateTest = XDate.addDays(date, 3);
        
        String dateStrExpected = "15/08/2003";
        Date dateExpected = XDate.toDate(dateStrExpected, format);
        
        assertEquals(dateExpected,dateTest );
    }
    
      @Test
    public void testNowFunction(){
      
        assertEquals(new Date(),XDate.now() );
    }

 
}

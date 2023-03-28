package com.dejavu.test.model;

import com.bar.model.BanOder;
import org.junit.Test;
import static org.junit.Assert.*;

public class BanOderModelTest {

    BanOder banoder;

    public BanOderModelTest() {
    }

    @Test
    public void testModelBanOderConstructor() {
        banoder = new BanOder("B88", "Thêm Mì", "B01", "ABC");
        assertEquals(banoder.getMaBan(), "B88");
        assertEquals(banoder.getGhiChu(), "Thêm Mì");
        assertEquals(banoder.getMaBanGop(), "B01");
        assertEquals(banoder.getVoucher(), "ABC");

    }

    @Test
    public void testModelBanOderGetterAndSetter() {
        banoder = new BanOder();
        banoder.setMaBan("B88");
        banoder.setGhiChu("Thêm Mì");
        banoder.setMaBanGop("B01");
        banoder.setVoucher("ABC");
        assertEquals(banoder.getMaBan(), "B88");
        assertEquals(banoder.getGhiChu(), "Thêm Mì");
        assertEquals(banoder.getMaBanGop(), "B01");
        assertEquals(banoder.getVoucher(), "ABC");

    }

}

package com.dejavu.test.model;

import com.bar.model.Ban;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thaih
 */
public class banModelTest {

    Ban ban;

    public banModelTest() {
    }
    
    @Test
    public void testModelBanConstructor() {
        ban = new Ban("B88", true, "NV01");
        assertEquals(ban.getMaBan(), "B88");
        assertEquals(ban.isTrangThai(), true);
        assertEquals(ban.getMaNhanVien(), "NV01");
    }
    
  @Test
    public void testModelBanGetter() {
        ban = new Ban();
        ban.setMaBan("B88");
        ban.setTrangThai(true);
        ban.setMaNhanVien("NV01");
        assertEquals(ban.getMaBan(), "B88");
        assertEquals(ban.isTrangThai(), true);
        assertEquals(ban.getMaNhanVien(), "NV01");
    }
    @Test
    public void testModelBanSetter() {
        ban = new Ban();
        ban.setMaBan("B88");
        ban.setTrangThai(true);
        ban.setMaNhanVien("NV01");
        assertEquals(ban.getMaBan(), "B88");
        assertEquals(ban.isTrangThai(), true);
        assertEquals(ban.getMaNhanVien(), "NV01");
    }
    
}

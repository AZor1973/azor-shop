package ru.azor.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.azor.config.ShopConfig;
import ru.azor.entity.Manufacturer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(ShopConfig.class)
class ManufacturerDaoTest {

    @Autowired
    ManufacturerDao manufacturerDao;

    @Test
    public void saveTest() {
        Manufacturer manufacturer = Manufacturer.builder()
                .name("Tesla").build();

        Manufacturer savedManufacturer = manufacturerDao.save(manufacturer);

        assertAll(
                () -> assertEquals(1L, savedManufacturer.getId()),
                () -> assertEquals("Tesla", savedManufacturer.getName()),
                () -> assertEquals(0, savedManufacturer.getVersion()),
                () -> assertEquals("User", savedManufacturer.getCreatedBy(), "createdBy mustn't be null"),
                () -> assertEquals("User", savedManufacturer.getLastModifiedBy(), "lastModified mustn't be null"),
                () -> assertNotNull(savedManufacturer.getCreatedDate(), "createdDate mustn't be null"),
                () -> assertNotNull(savedManufacturer.getLastModifiedDate(), "lastModifiedDate mustn't be null")
        );

    }

}

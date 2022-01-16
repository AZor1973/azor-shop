package ru.azor.web.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.azor.dao.CategoryDao;
import ru.azor.dao.ManufacturerDao;
import ru.azor.entity.Category;
import ru.azor.entity.Manufacturer;
import ru.azor.entity.Product;
import ru.azor.web.dto.ProductDto;
import ru.azor.web.dto.ProductManufacturerDto;

import java.util.NoSuchElementException;

@Mapper(uses = ManufacturerMapper.class)
public interface ProductMapper {
    Product toProduct(ProductDto productDto, @Context ManufacturerDao manufacturerDao, @Context CategoryDao categoryDao);

    ProductDto toProductDto(Product product);

    @Mapping(source = "manufacturer", target = "manufacturerDto")
    ProductManufacturerDto toProductManufacturerDto(Product product);

    default Manufacturer getManufacturer(String manufacturer, @Context ManufacturerDao manufacturerDao) {
        return manufacturerDao.findByName(manufacturer).orElseThrow(NoSuchElementException::new);
    }

    default String getManufacturer(Manufacturer manufacturer) {
        return manufacturer.getName();
    }

    default Category getCategory(String category, @Context CategoryDao categoryDao) {
        return categoryDao.findByTitle(category).orElseThrow(NoSuchElementException::new);
    }

    default String getCategory(Category category) {
        return category.getTitle();
    }
}

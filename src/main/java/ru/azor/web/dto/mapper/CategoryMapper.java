package ru.azor.web.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.azor.entity.Category;
import ru.azor.web.dto.CategoryDto;

@Mapper
public interface CategoryMapper {
    @Mapping(source = "categoryId", target = "id")
    Category toCategory(CategoryDto categoryDto);

    @Mapping(source = "id", target = "categoryId")
    CategoryDto toCategoryDto(Category category);
}

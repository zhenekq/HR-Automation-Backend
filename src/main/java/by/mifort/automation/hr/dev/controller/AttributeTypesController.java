package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.service.AttributeTypesService;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Controller that handles requests about attribute types
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/attributetypes")
@Api("Controller for manipulate with attribute types")
public class AttributeTypesController {

    private final AttributeTypesService service;
    private final EntityConverter<AttributeTypes, AttributeTypesDto> converter;

    @Autowired
    public AttributeTypesController(AttributeTypesService service, EntityConverter<AttributeTypes, AttributeTypesDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    /**
     * GET request to get attribute types
     *
     * @return List of attribute types
     */
    @ApiOperation("Get paginated attribute types")
    @GetMapping
    public List<AttributeTypesDto> getAll(FilterDto filterDto) {
        Integer pageNumber = filterDto.getPageNumber();
        Integer pageSize = filterDto.getPageSize();
        if (pageNumber == null || pageSize == null || pageNumber <= 0 || pageSize <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters cannot be nullable");
        }
        List<AttributeTypes> attributeTypes = service.getAll(filterDto);
        return converter.convertToListEntityDto(attributeTypes);
    }

    /**
     * POST request to create new attribute type
     *
     * @param type attribute type body
     * @return created attribute type
     */
    @ApiOperation("Create new attribute type")
    @PostMapping
    public AttributeTypesDto create(@RequestBody AttributeTypesDto type) {
        AttributeTypes types = service.create(converter.convertToEntity(type));
        return converter.convertToEntityDto(types);
    }

    @ApiOperation("Get attribute type by id")
    @GetMapping("{id}")
    public AttributeTypesDto getById(@PathVariable Integer id){
        AttributeTypes attributeTypes = service.getById(id);
        return converter.convertToEntityDto(attributeTypes);
    }

    /**
     * PATCH request to update attribute type
     *
     * @param id attribute type identifier
     * @return updated attribute type
     */
    @ApiOperation("Update attribute type")
    @PatchMapping("{id}")
    public AttributeTypesDto updateByAttributeId(@PathVariable Integer id,
                                                 @RequestBody AttributeTypesDto type) {
        AttributeTypes types = service.updateById(id, type);
        return converter.convertToEntityDto(types);
    }

    /**
     * DELETE request to archive attribute type
     *
     * @param id attribute type identifier
     * @return attribute type
     */
    @ApiOperation("Archive attribute type")
    @DeleteMapping("{id}")
    public AttributeTypesDto deleteByAttributeId(@PathVariable Integer id) {
        AttributeTypes types = service.archiveById(id);
        return converter.convertToEntityDto(types);
    }
}

package by.mifort.automation.hr.dev.type;

import by.mifort.automation.hr.dev.type.request.CreateNewAttributeType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller that handles requests about attribute types
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/attributetypes")
@Api("Controller for manipulate with attribute types")
@Validated
public class AttributeTypesController {

    private final AttributeTypesService service;
    private final ModelMapper modelMapper;

    @Autowired
    public AttributeTypesController(AttributeTypesService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    /**
     * GET request to get attribute types
     *
     * @return List of attribute types
     */
    @ApiOperation("Get paginated attribute types")
    @GetMapping
    public List<AttributeTypesDto> getAll() {
        List<AttributeTypes> attributeTypes = service.getAll();
        List<AttributeTypesDto> list = attributeTypes
                .stream()
                .map(el -> modelMapper.map(el, AttributeTypesDto.class))
                .collect(Collectors.toList());

        AttributeTypeUtil.addLabelList(attributeTypes, list);
        return list;
    }

    /**
     * POST request to create new attribute type
     *
     * @param typeDto attribute type body
     * @return created attribute type
     */
    @ApiOperation("Create new attribute type")
    @PostMapping
    public AttributeTypesDto create(@Valid @RequestBody CreateNewAttributeType typeDto) {
        AttributeTypes attributeType = modelMapper.map(typeDto, AttributeTypes.class);
        AttributeTypes createdAttribute = service.create(attributeType);
        AttributeTypesDto dto = modelMapper.map(createdAttribute, AttributeTypesDto.class);
        AttributeTypeUtil.addLabel(createdAttribute, dto);
        return dto;
    }

    @ApiOperation("Get attribute type by id")
    @GetMapping("{id}")
    public AttributeTypesDto getById(@PathVariable Integer id) {
        AttributeTypes attributeType = service.getById(id);
        AttributeTypesDto dto = modelMapper.map(attributeType, AttributeTypesDto.class);
        AttributeTypeUtil.addLabel(attributeType, dto);
        return dto;
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
                                                 @RequestBody AttributeTypesDto typesDto) {
        AttributeTypes type = modelMapper.map(typesDto, AttributeTypes.class);
        AttributeTypes updatedAttribute = service.updateById(id, type);
        return modelMapper.map(updatedAttribute, AttributeTypesDto.class);
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
        AttributeTypes deletedType = service.archiveById(id);
        return modelMapper.map(deletedType, AttributeTypesDto.class);
    }
}

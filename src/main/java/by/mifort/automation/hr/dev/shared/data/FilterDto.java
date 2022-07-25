package by.mifort.automation.hr.dev.shared.data;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FilterDto extends PaginationDto {

    private List<String> keyword;
    private Boolean isArchived;

    public FilterDto(Integer pageNumber, Integer pageSize) {
        super(pageNumber, pageSize);
    }

}

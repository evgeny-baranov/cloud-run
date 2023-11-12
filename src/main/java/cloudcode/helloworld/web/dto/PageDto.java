package cloudcode.helloworld.web.dto;

import com.lp.domain.model.SortDirectionEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PageDto<T> {
    private SortDirectionEnum sortDirection;
    private String sortBy;
    private Integer pageNum;
    private Collection<T> data = new ArrayList<>();
}

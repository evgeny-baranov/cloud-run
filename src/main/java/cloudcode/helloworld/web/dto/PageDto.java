package cloudcode.helloworld.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PageDto<T> {
    private int number;
    private int totalPages;
    private long totalElements;
    private SortDto sort;
    private Collection<T> data = new ArrayList<>();
}

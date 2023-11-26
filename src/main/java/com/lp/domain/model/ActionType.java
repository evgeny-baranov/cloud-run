package com.lp.domain.model;

import com.lp.domain.model.converter.StringListToJsonConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "action_type", catalog = "testdb")
@Getter
@Setter
@RequiredArgsConstructor
public class ActionType extends AbstractEntity {
    @Enumerated
    ActionTypeEnum name;

    @Convert(converter = StringListToJsonConverter.class)
    List<String> settings = new ArrayList<>();

    public ActionType(ActionTypeEnum actionTypeName) {
        name = actionTypeName;
    }
}

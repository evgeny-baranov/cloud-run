package com.lp.domain.model;

import com.lp.domain.model.converter.StringListToJsonConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
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

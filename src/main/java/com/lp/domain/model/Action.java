package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lp.domain.model.converter.SettingsConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Action extends AbstractUuidEntity {

    String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    Campaign campaign;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    ActionType type;

    @Convert(converter = SettingsConverter.class)
    Settings settings = new Settings();

    public void setType(ActionTypeEnum typeEnum) {
        this.type = new ActionType(typeEnum);
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), campaign, type);
    }
}

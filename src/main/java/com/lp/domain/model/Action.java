package com.lp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lp.domain.model.converter.SettingsConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "action", catalog = "testdb")
@Getter
@Setter
@RequiredArgsConstructor
public class Action extends AbstractUuidEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "campaign_id")
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
}

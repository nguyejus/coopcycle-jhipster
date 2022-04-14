package com.nguyejus.coopcycle.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.nguyejus.coopcycle.domain.Menu} entity.
 */
public class MenuDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer iDmenu;

    @NotNull
    private Integer iDcooperative;

    private ZonedDateTime lastupdate;

    private CooperativeDTO cooperative;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDmenu() {
        return iDmenu;
    }

    public void setiDmenu(Integer iDmenu) {
        this.iDmenu = iDmenu;
    }

    public Integer getiDcooperative() {
        return iDcooperative;
    }

    public void setiDcooperative(Integer iDcooperative) {
        this.iDcooperative = iDcooperative;
    }

    public ZonedDateTime getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(ZonedDateTime lastupdate) {
        this.lastupdate = lastupdate;
    }

    public CooperativeDTO getCooperative() {
        return cooperative;
    }

    public void setCooperative(CooperativeDTO cooperative) {
        this.cooperative = cooperative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuDTO)) {
            return false;
        }

        MenuDTO menuDTO = (MenuDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, menuDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuDTO{" +
            "id=" + getId() +
            ", iDmenu=" + getiDmenu() +
            ", iDcooperative=" + getiDcooperative() +
            ", lastupdate='" + getLastupdate() + "'" +
            ", cooperative=" + getCooperative() +
            "}";
    }
}

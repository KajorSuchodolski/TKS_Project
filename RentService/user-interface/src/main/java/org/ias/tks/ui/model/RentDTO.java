package org.ias.tks.ui.model;

import org.ias.tks.ui.model.costume.CostumeDTO;
import org.ias.tks.ui.model.user.UserDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class RentDTO extends EntityDTO {

    private UUID id;
    private UserDTO user;
    private List<CostumeDTO> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime;


    public RentDTO() {

    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser( UserDTO user ) {
        this.user = user;
    }

    public List<CostumeDTO> getCostumes() {
        return costumes;
    }

    public void setCostumes( List<CostumeDTO> costumes ) {
        this.costumes = costumes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    public LocalDate getBeginTime() {
        return beginTime;
    }

    public void setBeginTime( LocalDate beginTime ) {
        this.beginTime = beginTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime( LocalDate endTime ) {
        this.endTime = endTime;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getUserLogin() {
        return user.getLogin();
    }

    public List<String> getCostumeName() {
        return costumes
                .stream()
                .map(CostumeDTO::getName)
                .collect(Collectors.toList());
    }

//    @Override
//    public boolean equals( Object o ) {
//        if( this == o ) return true;
//        if( !(o instanceof RentDTO rentDTO) ) return false;
//        if( !super.equals(o) ) return false;
//        return Double.compare(rentDTO.price, price) == 0 && Objects.equals(user, rentDTO.user) && Objects.equals(costumes, rentDTO.costumes) && Objects.equals(beginTime, rentDTO.beginTime) && Objects.equals(endTime, rentDTO.endTime);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, costumes, price, beginTime, endTime);
    }

    @Override
    public String toString() {
        return "RentDTO{" +
                "user=" + user +
                ", costumes=" + costumes +
                ", price=" + price +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}

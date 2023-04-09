package entity;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Socks {
    private String color;
    private Long cottonPart;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Long cottonPart) {
        this.cottonPart = cottonPart;
    }

   }

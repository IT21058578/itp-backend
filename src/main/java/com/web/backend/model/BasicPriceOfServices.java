package com.web.backend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "BasicPrices")
public class BasicPriceOfServices {


    @Id
    private long id;
    @Field
    private double roof;
    @Field
    private double kitchen;
    @Field
    private double bathRoom;
    @Field
    private double bedRoom;
    @Field
    private double livingRoom;
    @Field
    private double guestRoom;
    @Field
    private double chimney;
    @Field
    private double floor;
    @Field
    private double celling;
    @Field
    private double wallPainting;
    @Field
    private final double loundaryAdvance;
    @Field
    private final double roomSenitize;
    @Field
    private double divisionSenitize;
    @Field
    private double blockSenitize;
    @Field
    private double carpet;
    @Field
    private double wd;



    public BasicPriceOfServices(long id, double wd, double carpet, double roof, double kitchen, double bathRoom,
                                double bedRoom,
                                double livingRoom, double guestRoom, double chimney, double floor, double celling,
                                double wallPainting, double loundaryAdvance, double roomSenitize, double divisionSenitize, double blockSenitize) {
        this.id = id;
        this.roof = roof;
        this.kitchen = kitchen;
        this.bathRoom = bathRoom;
        this.bedRoom = bedRoom;
        this.livingRoom = livingRoom;
        this.guestRoom = guestRoom;
        this.chimney = chimney;
        this.floor = floor;
        this.celling = celling;
        this.wallPainting = wallPainting;
        this.loundaryAdvance = loundaryAdvance;
        this.roomSenitize = roomSenitize;
        this.divisionSenitize = divisionSenitize;
        this.blockSenitize = blockSenitize;
        this.carpet = carpet;
        this.wd = wd;
}

    public long getId() {
        return id;
    }

    public double getCarpet() {
        return carpet;
    }

    public double getWd() {
        return wd;
    }

    public double getRoof() {
        return roof;
    }

    public double getLoundaryAdvance() {
        return loundaryAdvance;
    }

    public double getRoomSenitize() {
        return roomSenitize;
    }

    public double getDivisionSenitize() {
        return divisionSenitize;
    }

    public double getBlockSenitize() {
        return blockSenitize;
    }

    public double getKitchen() {
        return kitchen;
    }

    public double getBathroom() {
        return bathRoom;
    }

    public double getBedroom() {
        return bedRoom;
    }

    public double getLivingroom() {
        return livingRoom;
    }

    public double getGuestroom() {
        return guestRoom;
    }

    public double getChimney() {
        return chimney;
    }

    public double getFloor() {
        return floor;
    }

    public double getCelling() {
        return celling;
    }

    public double getWallPainting() {
        return wallPainting;
    }

}

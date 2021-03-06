package com.example.igiagante.thegarden.core.repository.realm.modelRealm;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @author Ignacio Giagante, on 3/7/16.
 */
public class GardenRealm extends RealmObject {

    @PrimaryKey
    private String id;

    private String userId;

    @Required
    private String name;

    private Date startDate;

    private Date endDate;

    private RealmList<PlantRealm> plants;

    private RealmList<IrrigationRealm> irrigations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public RealmList<PlantRealm> getPlants() {
        return plants;
    }

    public void setPlants(RealmList<PlantRealm> plants) {
        this.plants = plants;
    }

    public RealmList<IrrigationRealm> getIrrigations() {
        return irrigations;
    }

    public void setIrrigations(RealmList<IrrigationRealm> irrigations) {
        this.irrigations = irrigations;
    }
}

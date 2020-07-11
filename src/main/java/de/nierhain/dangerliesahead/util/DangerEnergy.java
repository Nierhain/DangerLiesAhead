package de.nierhain.dangerliesahead.util;

import net.minecraftforge.energy.EnergyStorage;

public class DangerEnergy extends EnergyStorage {

    public DangerEnergy(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }

    public void consumePower(int energy){
        this.energy -= energy;
        if(this.energy < 0){
            this.energy = 0;
        }
    }
}

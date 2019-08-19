package de.nierhain.danger.capabilities.spawned;

public class DefaultSpawned implements ISpawned {

    private boolean isSpawned = false;
    @Override
    public boolean isSpawned() {
        return isSpawned;
    }

    @Override
    public void setSpawned(boolean spawned) {
        isSpawned = spawned;
    }
}

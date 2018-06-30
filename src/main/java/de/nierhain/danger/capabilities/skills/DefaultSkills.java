package de.nierhain.danger.capabilities.skills;


public class DefaultSkills implements ISkills {


    private int health;
    private int hunger;
    private int movementSpeed;
    private int attackDamage;
    private int attackSpeed;
    private int luck;

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHunger() {
        return hunger;
    }

    @Override
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public int getAttackDamage() {
        return attackDamage;
    }

    @Override
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    @Override
    public int getLuck() {
        return luck;
    }

    @Override
    public void setLuck(int luck) {
        this.luck = luck;
    }
}

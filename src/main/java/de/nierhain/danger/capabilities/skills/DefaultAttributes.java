package de.nierhain.danger.capabilities.skills;


public class DefaultAttributes implements IAttributes {

    private int skillpoints = 0;
    private int health = 0;
    private int hunger = 0;
    private int movementSpeed = 0;
    private int attackDamage = 0;
    private int attackSpeed = 0;
    private int luck = 0;

    @Override
    public int getSkillpoints(){
        return skillpoints;
    }

    @Override
    public void setSkillpoints(int skillpoints){
        this.skillpoints = skillpoints;
    }

    @Override
    public void removeSkillpoint(){
        if(this.skillpoints > 0) {
            skillpoints -= 1;
        }
    }
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

    @Override
    public void reset() {
        skillpoints = 0;
        health = 0;
        hunger = 0;
        movementSpeed = 0;
        attackDamage = 0;
        attackSpeed = 0;
        luck = 0;
    }
}

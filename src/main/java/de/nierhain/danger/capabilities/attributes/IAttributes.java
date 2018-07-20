package de.nierhain.danger.capabilities.attributes;

public interface IAttributes {

    int getSkillpoints();
    void addSkillpoints(int skillpoints);
    void setSkillpoints(int skillpoints);
    void removeSkillpoint();

    int getHealth();
    void setHealth(int health);

    int getHunger();
    void setHunger(int hunger);

    int getMovementSpeed();
    void setMovementSpeed(int movementSpeed);

    int getAttackDamage();
    void setAttackDamage(int attackDamage);

    int getAttackSpeed();
    void setAttackSpeed(int attackSpeed);

    int getLuck();
    void setLuck(int luck);

    void reset();
}

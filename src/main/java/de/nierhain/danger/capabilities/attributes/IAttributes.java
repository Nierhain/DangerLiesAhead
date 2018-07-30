package de.nierhain.danger.capabilities.attributes;

import de.nierhain.danger.enums.Attribute;

public interface IAttributes {

    int getSkillpoints();
    void setSkillpoints(int skillpoints);
    void removeSkillpoint();

    int getAttribute(Attribute attr);
    void setAttribute(Attribute attr, int amount);

    int[] getAllAttributes();
    void setAllAttributes(int[] amount);

    void reset();
}

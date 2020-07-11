package de.nierhain.dangerliesahead.capabilities.attributes;


import de.nierhain.dangerliesahead.enums.Attribute;

public class DefaultAttributes implements IAttributes {

    private int skillpoints = 0;
    private int[] attributes = new int[5];

    @Override
    public int getSkillpoints(){
        return skillpoints;
    }

    @Override
    public void setSkillpoints(int skillpoints){
        this.skillpoints = skillpoints;
    }

    public void addSkillpoints(int skillpoints) { this.skillpoints += skillpoints; }

    @Override
    public void removeSkillpoint(){
        if(this.skillpoints > 0) {
            skillpoints -= 1;
        }
    }

    public int getAttribute(Attribute attr){
        return attributes[attr.getValue()];
    }

    public void setAttribute(Attribute attr, int amount){
        this.attributes[attr.getValue()] = amount;
    }

    public int[] getAllAttributes(){
        return attributes;
    }

    public void setAllAttributes(int[] amount){
        this.attributes = amount;
    }

    @Override
    public void reset() {
        skillpoints = 0;
        for(int i = 0; i < attributes.length; i++){
            attributes[i] = 0;
        }
    }
}

package de.nierhain.danger.util;

import net.minecraft.launchwrapper.Launch;

public class DevEnv {
    public static boolean isDevEnv() {
        return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}

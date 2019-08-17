package de.nierhain.dangerliesahead.handler;


import de.nierhain.dangerliesahead.gui.GuiSkill;
import de.nierhain.dangerliesahead.gui.GuiStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

public class KeyHandler {

    public static KeyBinding guiKey;
    public static KeyBinding testKey;

    public KeyHandler(){
        this.init();
    }

    public static void init(){
        guiKey = new KeyBinding("key.gui.desc", Keyboard.KEY_X, "key.categories.dangerliesahead");
        testKey = new KeyBinding("key.gui.test", Keyboard.KEY_Y, "key.categories.dangerliesahead");
        ClientRegistry.registerKeyBinding(guiKey);
        ClientRegistry.registerKeyBinding(testKey);
    }

    @SubscribeEvent()
    public void onKeyInput(InputEvent.KeyInputEvent event){
        if(Minecraft.getMinecraft().inGameHasFocus){
            if(guiKey.isPressed()){
                Minecraft.getMinecraft().displayGuiScreen(new GuiSkill());
            }
            if(testKey.isPressed()){
                Minecraft.getMinecraft().displayGuiScreen(new GuiStatus());
            }
        }
    }
}


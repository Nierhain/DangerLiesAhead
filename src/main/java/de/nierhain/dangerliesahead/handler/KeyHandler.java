package de.nierhain.dangerliesahead.handler;


import de.nierhain.dangerliesahead.gui.GuiStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

public class KeyHandler {

    private final Minecraft mc;

    private static final KeyBinding[] binds = new KeyBinding[5];

    public KeyHandler(Minecraft mc){
        this.mc = mc;

        binds[0] = new KeyBinding("key.gui.desc", Keyboard.KEY_X, "key.danger.category");
        ClientRegistry.registerKeyBinding(binds[0]);
    }

    @SubscribeEvent()
    public void onKeyInput(KeyInputEvent event){
        if(mc.inGameHasFocus){
            if(binds[0].isPressed()){
                mc.displayGuiScreen(new GuiStatus());
            }
        }
    }
}


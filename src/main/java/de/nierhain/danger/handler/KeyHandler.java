package de.nierhain.danger.handler;


import de.nierhain.danger.gui.GuiSkill;
import de.nierhain.danger.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onKeyInput(InputEvent.KeyInputEvent event){
        KeyBinding[] binds = ClientProxy.getBinds();

        if(binds[0].isPressed()){
            System.out.println("Key pressed");
            Minecraft.getMinecraft().displayGuiScreen(new GuiSkill());
        }
    }
}


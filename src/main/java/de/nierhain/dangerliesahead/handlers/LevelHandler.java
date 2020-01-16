package de.nierhain.dangerliesahead.handlers;

import de.nierhain.dangerliesahead.DangerLiesAhead;
import de.nierhain.dangerliesahead.capabilities.CapabilityLevel;
import de.nierhain.dangerliesahead.capabilities.ILevel;
import de.nierhain.dangerliesahead.gui.TestGUI;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.network.PacketLevel;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

import java.util.function.Supplier;

import static de.nierhain.dangerliesahead.capabilities.CapabilityLevel.LEVEL;

@Mod.EventBusSubscriber
public class LevelHandler {

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event){
        if(event.getKey() == GLFW.GLFW_KEY_Y){
            Minecraft.getInstance().displayGuiScreen(new TestGUI());
        }
    }

    @SubscribeEvent
    public static void attachLevel(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof PlayerEntity){
            event.addCapability(new ResourceLocation(DangerLiesAhead.MODID, "level"), new CapabilityLevel());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            ILevel oldEntity = getHandler(event.getEntity());
            ILevel newEntity = getHandler(event.getEntity());

            newEntity.setXP(oldEntity.getXP());
            newEntity.setLevel(oldEntity.getLevel());
        }
    }

    @SubscribeEvent
    public static void onXPPickup(PlayerXpEvent.PickupXp event){
        ILevel playerLevel = getHandler(event.getEntity());
        playerLevel.addXP(event.getOrb().xpValue);
       // synchronize((ServerPlayerEntity) event.getEntity(), playerLevel);
    }

    private static void synchronize(ServerPlayerEntity player, ILevel levelObj){

        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(new Supplier<ServerPlayerEntity>() {
            @Override
            public ServerPlayerEntity get() {
                return player;
            }
        }), new PacketLevel(levelObj.getLevel(), levelObj.getXP()));
    }

    private static ILevel getHandler(Entity entity) {
       return entity.getCapability(LEVEL, null).orElse(null);
    }

}

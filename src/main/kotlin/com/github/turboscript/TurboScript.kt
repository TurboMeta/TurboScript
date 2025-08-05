package com.github.turboscript

import com.github.turboscript.config.Configs
import com.github.turboscript.graphics.TRenderSystem
import com.github.turboscript.gui.ConfigGUI
import com.mojang.blaze3d.platform.InputConstants
import com.mojang.logging.LogUtils
import net.minecraft.client.KeyMapping
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import net.neoforged.neoforge.client.settings.KeyConflictContext
import net.neoforged.neoforge.common.NeoForge
import org.lwjgl.glfw.GLFW
import org.slf4j.Logger

@Mod(TurboScript.MOD_ID, dist = [Dist.CLIENT])
class TurboScript(
    modEventBus: IEventBus,
    modContainer: ModContainer
) {

    companion object {

        internal const val MOD_ID = "turboscript"
        internal val LOGGER: Logger = LogUtils.getLogger()

        internal val uiKey by lazy { KeyMapping(
            "turboscript.key.ui",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            "key.categories.misc"
        ) }

    }

    init {
        modEventBus.addListener<FMLCommonSetupEvent> {
            LOGGER.info("TurboScript is running!")
        }

        modEventBus.addListener<RegisterKeyMappingsEvent> { event ->
            event.register(uiKey)
        }

        NeoForge.EVENT_BUS.register(TRenderSystem)
        NeoForge.EVENT_BUS.register(ConfigGUI)

        modContainer.registerConfig(ModConfig.Type.COMMON, Configs.spec)
        JavaWrapper.registerExtensionPointConfig(modContainer)
    }

}
package com.github.turboscript

import com.github.turboscript.config.Configs
import com.mojang.logging.LogUtils
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import org.slf4j.Logger

@Mod(TurboScript.MOD_ID, dist = [Dist.CLIENT])
class TurboScript(
    modEventBus: IEventBus,
    modContainer: ModContainer
) {

    init {
        NAMESPACE = modContainer.namespace
        modEventBus.addListener(this::commonSetup)

        modContainer.registerConfig(ModConfig.Type.COMMON, Configs.spec)
        JavaWrapper.registerExtensionPointConfig(modContainer)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        LOGGER.info("TurboScript is running!")
    }

    companion object {

        internal const val MOD_ID = "turboscript"
        internal val LOGGER: Logger = LogUtils.getLogger()
        internal lateinit var NAMESPACE: String; private set

    }
}
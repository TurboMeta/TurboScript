package com.github.turboscript

import com.mojang.logging.LogUtils
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import org.slf4j.Logger

@Mod(TurboScript.MOD_ID, dist = [Dist.CLIENT])
class TurboScript(
    modEventBus: IEventBus,
    modContainer: ModContainer
) {

    init {
        modEventBus.addListener(this::commonSetup)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        LOGGER.info(event.description())
    }

    companion object {

        const val MOD_ID = "turboscript"
        val LOGGER: Logger = LogUtils.getLogger()

    }
}
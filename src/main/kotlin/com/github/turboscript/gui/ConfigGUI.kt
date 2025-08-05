package com.github.turboscript.gui

import com.github.turboscript.TurboScript
import com.github.turboscript.graphics.TRenderSystem
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.InputEvent

object ConfigGUI: Screen(Component.literal("TurboScript-ConfigGUI")) {

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        TRenderSystem.render {

        }
        super.render(guiGraphics, mouseX, mouseY, partialTick)
    }

    override fun shouldCloseOnEsc(): Boolean = true
    override fun isPauseScreen(): Boolean = false

    @SubscribeEvent
    @Suppress("UNUSED_PARAMETER")
    fun onKeyPressed(event: InputEvent.Key) {
        if (TurboScript.uiKey.isDown) {
            Minecraft.getInstance().setScreen(this)
        }
    }

}
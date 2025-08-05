package com.github.turboscript.graphics

import com.github.turboscript.config.Configs
import com.github.turboscript.config.RenderApi
import com.github.turboscript.graphics.ogl.GlRenderFunctions
import com.github.turboscript.graphics.IRenderFunctions
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RenderGuiEvent

object TRenderSystem {

    internal val renderApi: RenderApi
        get() = Configs.get().renderApi.get()

    private var renderFunctions: MutableMap<RenderApi, IRenderFunctions> = mutableMapOf()
    private val currentRenderFunctions get() = renderFunctions[renderApi]

    internal fun render(func: RenderContext.() -> Unit) {
        beginRender()
        RenderContext.instance?.let { func.invoke(it) }
        endRender()
    }

    @SubscribeEvent
    fun onPreRenderGui(event: RenderGuiEvent.Pre) {
        refreshRenderFunctions()
    }

    private fun refreshRenderFunctions() {
        renderFunctions[renderApi]?.let { return }
        renderFunctions.clear()
        renderFunctions[renderApi] = when (renderApi) {
            RenderApi.OPENGL -> GlRenderFunctions
        }
    }

    private fun beginRender() { currentRenderFunctions?.beginRender() }
    private fun endRender() { currentRenderFunctions?.endRender() }

}
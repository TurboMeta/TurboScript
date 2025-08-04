package com.github.turboscript.graphics

import com.github.turboscript.config.Configs
import com.github.turboscript.config.RenderApi
import com.github.turboscript.graphics.ogl.TOpenGlRenderSystem

object TRenderSystem {

    internal val renderApi: RenderApi
        get() = Configs.get().renderApi.get()

    internal fun beginRender() {
        when (renderApi) {
            RenderApi.OPENGL -> TOpenGlRenderSystem.beginRender()
        }
    }

    internal fun endRender() {
        when (renderApi) {
            RenderApi.OPENGL -> TOpenGlRenderSystem.endRender()
        }
    }

}
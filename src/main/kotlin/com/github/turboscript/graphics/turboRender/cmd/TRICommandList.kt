package com.github.turboscript.graphics.turboRender.cmd

import com.github.turboscript.config.RenderApi
import com.github.turboscript.graphics.TRenderSystem
import com.github.turboscript.graphics.ogl.cmd.GlCommandList
import com.github.turboscript.graphics.turboRender.TRIObject

interface TRICommandList: TRIObject {

    companion object {
        fun create(): TRICommandList =
            when (TRenderSystem.renderApi) {
                RenderApi.OPENGL -> GlCommandList()
            }
    }

    fun createRenderPass(): TRIRenderPass

}
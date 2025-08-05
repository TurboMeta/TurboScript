package com.github.turboscript.graphics.turboRender.cmd

import com.github.turboscript.config.RenderApi
import com.github.turboscript.graphics.TRenderSystem
import com.github.turboscript.graphics.ogl.cmd.GlDescriptorSet
import com.github.turboscript.graphics.turboRender.TRIObject

interface TRIDescriptorSet: TRIObject {

    companion object {
        fun create(): TRIDescriptorSet =
            when (TRenderSystem.renderApi) {
                RenderApi.OPENGL -> GlDescriptorSet()
            }
    }

    fun addLayout(layout: TRIDescriptorSetLayout)

    fun getLayouts(): List<TRIDescriptorSetLayout>

}
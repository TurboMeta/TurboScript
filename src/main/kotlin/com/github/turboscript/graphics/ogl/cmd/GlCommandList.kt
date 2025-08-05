package com.github.turboscript.graphics.ogl.cmd

import com.github.turboscript.graphics.turboRender.cmd.TRICommandList

class GlCommandList: TRICommandList {

    override fun createRenderPass(): GlRenderPass {
        return GlRenderPass()
    }

}
package com.github.turboscript.graphics.ogl

import com.github.turboscript.graphics.IRenderFunctions

object GlRenderFunctions: IRenderFunctions {

    override fun beginRender() {
        OpenGlState.reset()
    }

    override fun endRender() {
    }

}
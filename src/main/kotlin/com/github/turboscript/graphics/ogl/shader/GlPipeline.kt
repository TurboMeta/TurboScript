package com.github.turboscript.graphics.ogl.shader

import com.github.turboscript.graphics.ogl.OpenGlState
import com.github.turboscript.graphics.turboRender.shader.TRIPipeline
import org.lwjgl.opengl.GL46.*

class GlPipeline(
    primitive: TRIPrimitive,
    pipelineStates: TRIPipelineStates,
    vertexShader: GlShaderModule,
    fragmentShader: GlShaderModule,
    geometryShader: GlShaderModule? = null,
): TRIPipeline(primitive, pipelineStates, vertexShader, fragmentShader, geometryShader) {

    fun bindStates() {
        pipelineStates.blendEnabled.let { OpenGlState.blend = it }
        pipelineStates.blendFunc.let { OpenGlState.blendFunc = when (it) {
            TRIBlendFunc.ONE -> GL_ONE
            TRIBlendFunc.ZERO -> GL_ZERO
            TRIBlendFunc.ONE_MINUS_SRC_COLOR -> GL_ONE_MINUS_SRC_COLOR
        } }

        pipelineStates.depthTestEnabled.let { OpenGlState.depth = it }
        pipelineStates.cullFaceEnabled.let { OpenGlState.cull = it }
    }

    val primitiveGl get() = when (primitive) {
        TRIPrimitive.TRIANGLES -> GL_TRIANGLES
        TRIPrimitive.TRIANGLE_STRIP -> GL_TRIANGLE_STRIP
        TRIPrimitive.TRIANGLE_FAN -> GL_TRIANGLE_FAN
        TRIPrimitive.LINES -> GL_LINES
        TRIPrimitive.LINE_STRIP -> GL_LINE_STRIP
        TRIPrimitive.LINE_LOOP -> GL_LINE_LOOP
    }

}
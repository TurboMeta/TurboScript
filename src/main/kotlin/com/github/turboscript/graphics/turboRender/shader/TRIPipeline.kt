package com.github.turboscript.graphics.turboRender.shader

open class TRIPipeline(
    val primitive: TRIPrimitive,
    protected val pipelineStates: TRIPipelineStates,
    protected val vertexShader: TRIShaderModule,
    protected val fragmentShader: TRIShaderModule,
    protected val geometryShader: TRIShaderModule? = null,
) {

    data class TRIPipelineStates(
        var depthTestEnabled: Boolean = true,
        var blendEnabled: Boolean = false,
        var blendFunc: TRIBlendFunc = TRIBlendFunc.ONE_MINUS_SRC_COLOR,
        var cullFaceEnabled: Boolean = false,
    )

    enum class TRIPrimitive {
        TRIANGLES,
        TRIANGLE_STRIP,
        TRIANGLE_FAN,
        LINES,
        LINE_STRIP,
        LINE_LOOP,
    }

    enum class TRIBlendFunc {
        ZERO,
        ONE,
        ONE_MINUS_SRC_COLOR,
    }

}
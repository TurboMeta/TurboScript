package com.github.turboscript.graphics.turboRender.shader

import com.github.turboscript.graphics.turboRender.TRIObject

abstract class TRIShaderModule(
    val source: TRIShaderSource,
    val type: TRIShaderType,
): TRIObject {

    abstract fun compile()

    enum class TRIShaderType {
        VERTEX,
        FRAGMENT,
        GEOMETRY,
        COMPUTE,
        TESSELLATION_CONTROL,
        TESSELLATION_EVALUATION
    }

}
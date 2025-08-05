package com.github.turboscript.graphics.turboRender.cmd

import com.github.turboscript.graphics.turboRender.TRIObject
import com.github.turboscript.graphics.turboRender.TRIResource

data class TRIDescriptorSetLayout(
    val binding: Int,
    val type: LayoutType,
    val target: TRIResource,
): TRIObject {

    enum class LayoutType {
        UNIFORM_BUFFER,
        STORAGE_BUFFER,
        SAMPLER,
    }

}
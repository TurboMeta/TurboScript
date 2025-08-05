package com.github.turboscript.graphics.ogl.cmd

import com.github.turboscript.graphics.turboRender.cmd.TRIDescriptorSet
import com.github.turboscript.graphics.turboRender.cmd.TRIDescriptorSetLayout

class GlDescriptorSet: TRIDescriptorSet {

    private val layouts = mutableListOf<TRIDescriptorSetLayout>()

    override fun addLayout(layout: TRIDescriptorSetLayout) {
        layouts.add(layout)
    }

    override fun getLayouts(): List<TRIDescriptorSetLayout> {
        return layouts
    }

}
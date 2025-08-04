package com.github.turboscript.graphics.ogl

import com.mojang.blaze3d.platform.GlStateManager
import org.lwjgl.opengl.GL46.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object OpenGlState {

    private val blend0 = GlState(false) {
        if (it) {
            GlStateManager._enableBlend()
            GlStateManager._blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        } else {
            GlStateManager._disableBlend()
        }
    }
    var blend by blend0

    private val depth0 = GlState(false) {
        if (it) GlStateManager._enableDepthTest()
        else GlStateManager._disableDepthTest()
    }
    var depth by depth0

    private val cull0 = GlState(false) {
        if (it) GlStateManager._enableCull()
        else GlStateManager._disableCull()
    }
    var cull by cull0

    private val lineSmooth0 = GlState(false) {
        if (it) glEnable(GL_LINE_SMOOTH)
        else glDisable(GL_LINE_SMOOTH)
    }
    var lineSmooth by lineSmooth0

    private val scissor0 = GlState(false) {
        if (it) GlStateManager._enableScissorTest()
        else GlStateManager._disableScissorTest()
    }
    var scissor by scissor0

    fun bindTexture(unit: Int, texture: Int) {
        GlStateManager._activeTexture(GL_TEXTURE0 + unit)
        GlStateManager._bindTexture(texture)
    }

    private val vertexArray0 = GlState(0) { glBindVertexArray(it) }
    var vertexArray by vertexArray0

    private val program0 = GlState(0) { glUseProgram(it) }
    var program by program0

    fun reset() {
        blend0.forceSetValue(false)
        depth0.forceSetValue(false)
        cull0.forceSetValue(false)
        lineSmooth0.forceSetValue(false)
        vertexArray0.forceSetValue(0)
        program0.forceSetValue(0)
        scissor0.forceSetValue(false)
    }

}

class GlState<T>(valueIn: T, private val action: (T) -> Unit) : ReadWriteProperty<Any?, T> {

    private var value = valueIn

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (this.value != value) {
            this.value = value
            action.invoke(value)
        }
    }

    fun forceSetValue(value: T) {
        this.value = value
        action.invoke(value)
    }

}

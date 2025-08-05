package com.github.turboscript.graphics.turboRender

interface TRIObject {

    /**
     * Returns the ID of the object.
     *
     * If the object is an **OpenGL** object, this ID can be used to identify it in the OpenGL context.
     *
     * If the object is **not** an **OpenGL** object, this ID can be used to identify it in the Turboscript context.
     * (Note: This ID is not necessarily unique across all objects in the Turboscript context.)
     * @return the ID of the object.
     */
    fun getId(): Int = 0

}
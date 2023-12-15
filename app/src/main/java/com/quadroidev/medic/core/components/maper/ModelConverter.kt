package com.quadroidev.medic.core.components.maper

class ModelConverter<Source, Target>(private val conversionFunc: (Source) -> Target) {
    companion object {
        inline fun <reified Source, reified Target> create(noinline convert: (Source) -> Target): ModelConverter<Source, Target> {
            return ModelConverter(convert)
        }
    }

    fun convert(source: Source): Target = performConversion(source)
    fun convertList(sourceList: Collection<Source>): Collection<Target> = sourceList.map { performConversion(it) }
    private fun performConversion(source: Source): Target = conversionFunc(source)
}
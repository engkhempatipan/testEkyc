package com.my.ekyc.data.mapper

import com.my.ekyc.data.model.ImageEntity
import com.my.ekyc.data.test.factory.Factory
import com.my.ekyc.domain.model.Image
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ImageMapperTest {

    private lateinit var mapper: ImageMapper

    @Before
    fun setUp() {
        mapper = ImageMapper()
    }

    @Test
    fun mapFromEntityMapsData() {
        // GIVEN
        val entity = Factory.makeImageEntity()
        // WHEN
        val domain = mapper.mapFromEntity(entity)
        // THEN
        assertDataEquality(entity, domain)
    }

    @Test
    fun mapToEntityMapsData() {
        // GIVEN
        val domain = Factory.makImage()
        // WHEN
        val entity = mapper.mapToEntity(domain)
        // THEN
        assertDataEquality(entity, domain)
    }

    private fun assertDataEquality(
        entity: ImageEntity,
        domain: Image
    ) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.url, domain.url)
        assertEquals(entity.mobile_id, domain.mobile_id)
    }

}
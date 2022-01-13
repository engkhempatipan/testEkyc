package com.my.ekyc.data.mapper

import com.my.ekyc.data.model.MobileEntity
import com.my.ekyc.data.test.factory.Factory
import com.my.ekyc.domain.model.Mobile
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MobileMapperTest {

    private lateinit var mapper: MobileMapper

    @Before
    fun setUp() {
        mapper = MobileMapper()
    }

    @Test
    fun mapFromEntityMapsData() {
        // GIVEN
        val entity = Factory.makeMobileEntity()
        // WHEN
        val domain = mapper.mapFromEntity(entity)
        // THEN
        assertDataEquality(entity, domain)
    }

    @Test
    fun mapToEntityMapsData() {
        // GIVEN
        val domain = Factory.makeMobile()
        // WHEN
        val entity = mapper.mapToEntity(domain)
        // THEN
        assertDataEquality(entity, domain)
    }

    private fun assertDataEquality(
        entity: MobileEntity,
        domain: Mobile
    ) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.thumbImageURL, domain.thumbImageURL)
        assertEquals(entity.description, domain.description)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.brand, domain.brand)
        assertEquals(entity.price, domain.price)
        assertEquals(entity.rating, domain.rating)
    }

}
package com.example.a8057043assignment2

import com.example.a8057043assignment2.data.model.Entity
import com.example.a8057043assignment2.data.repository.LoginRepository
import com.example.a8057043assignment2.ui.dashboard.DashboardViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private lateinit var repository: LoginRepository
    private lateinit var viewModel: DashboardViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `entity list starts empty and there is no error`() {
        assertTrue(viewModel.entities.value.isEmpty())
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun `successful load puts the entities into state`() = runTest(testDispatcher) {
        val firstEntity = mockk<Entity>()
        val secondEntity = mockk<Entity>()

        coEvery { repository.getDashboard(any()) } returns mockk {
            every { entities } returns listOf(firstEntity, secondEntity)
        }

        viewModel.loadDashboard("testKeypass123")
        advanceUntilIdle()

        assertEquals(2, viewModel.entities.value.size)
        assertEquals(firstEntity, viewModel.entities.value[0])
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun `an empty response leaves the list empty without an error`() = runTest(testDispatcher) {
        coEvery { repository.getDashboard(any()) } returns mockk {
            every { entities } returns emptyList()
        }

        viewModel.loadDashboard("testKeypass123")
        advanceUntilIdle()

        assertTrue(viewModel.entities.value.isEmpty())
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun `a failed load sets an error and leaves the list empty`() = runTest(testDispatcher) {
        coEvery { repository.getDashboard(any()) } throws RuntimeException("no network")

        viewModel.loadDashboard("testKeypass123")
        advanceUntilIdle()

        assertTrue(viewModel.entities.value.isEmpty())
        assertEquals("Could not load data.", viewModel.errorState.value)
    }

    @Test
    fun `the keypass is passed through to the repository`() = runTest(testDispatcher) {
        coEvery { repository.getDashboard(any()) } returns mockk {
            every { entities } returns emptyList()
        }

        viewModel.loadDashboard("testKeypass123")
        advanceUntilIdle()

        coVerify { repository.getDashboard("testKeypass123") }
    }
}
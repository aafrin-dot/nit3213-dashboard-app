package com.example.a8057043assignment2

import com.example.a8057043assignment2.data.repository.LoginRepository
import com.example.a8057043assignment2.ui.login.LoginViewModel
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
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var repository: LoginRepository
    private lateinit var viewModel: LoginViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `keypass and error are null before login is called`() {
        assertNull(viewModel.keypass.value)
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun `successful login stores the keypass`() = runTest(testDispatcher) {
        coEvery { repository.login(any(), any()) } returns mockk {
            every { keypass } returns "testKeypass123"
        }

        viewModel.login("testUser", "testPassword")
        advanceUntilIdle()

        assertEquals("testKeypass123", viewModel.keypass.value)
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun `failed login sets an error and leaves keypass null`() = runTest(testDispatcher) {
        coEvery { repository.login(any(), any()) } throws RuntimeException("401 unauthorised")

        viewModel.login("wrongUser", "wrongPassword")
        advanceUntilIdle()

        assertNull(viewModel.keypass.value)
        assertEquals("Login failed. Check your details.", viewModel.errorState.value)
    }

    @Test
    fun `login passes the entered credentials to the repository`() = runTest(testDispatcher) {
        coEvery { repository.login(any(), any()) } returns mockk {
            every { keypass } returns "abc"
        }

        viewModel.login("testUser", "testPassword")
        advanceUntilIdle()

        coVerify { repository.login("testUser", "testPassword") }
    }
}
package com.example.githupuser.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class TitleActivityModelTest {
    private lateinit var viewModel: TitleActivityModel
    private lateinit var title: LiveData<String>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TitleActivityModel()
    }

    @Test
    fun getTitle() {
        viewModel.updateActionBarTitle("Rizalm")
        title = viewModel.title
        assertEquals(title.value, "Rizalm")

    }

    @Test
    fun updateActionBarTitle() {
        viewModel.updateActionBarTitle("Rizalm")
        assertEquals(viewModel.title.value, "Rizalm")

    }
}
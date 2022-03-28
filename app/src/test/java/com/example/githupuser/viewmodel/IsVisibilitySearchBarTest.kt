package com.example.githupuser.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class IsVisibilitySearchBarTest {
    private lateinit var viewModel: IsVisibilitySearchBar
    private lateinit var isTrue: LiveData<Boolean>
    private lateinit var isFalse: LiveData<Boolean>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = IsVisibilitySearchBar()
    }

    @Test
    fun initVal(){
        isTrue = viewModel.isVisibility
        assertEquals(isTrue.value,true)
    }

    @Test
    fun setTrue() {
        viewModel.setTrue()
        isTrue = viewModel.isVisibility
        assertEquals(isTrue.value,true)
    }

    @Test
    fun setFalse() {
        viewModel.setFalse()
        isFalse = viewModel.isVisibility
        assertEquals(isFalse.value,false)
    }
}
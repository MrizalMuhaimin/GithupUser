package com.example.githupuser.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.githupuser.data.model.UserSearch
import com.example.githupuser.data.model.response.SearchResponse
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {
    private lateinit var viewModel: ListViewModel
    private lateinit var dataSearch : LiveData<SearchResponse>
    private lateinit var respondata : UserSearch

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ListViewModel()
    }

    @Test
    fun searchUser() {
        viewModel.searchUser("MrizalMuhaimin")
        dataSearch = viewModel.dataSearch
        assertNotNull(dataSearch)
    }
}
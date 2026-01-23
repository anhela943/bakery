package com.example.bakery.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bakery.Domain.CategoryModel
import com.example.bakery.Domain.ItemsModel
import com.example.bakery.Domain.SliderModel
import com.example.bakery.Repository.MainRepository

class MainViewModel(): ViewModel(){
    private val repository= MainRepository()

    fun loadBanner(): LiveData<MutableList<SliderModel>> {
        return repository.loadBanner()
    }
    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }
    fun loadBestSeller(): LiveData<MutableList<ItemsModel>> {
        return repository.loadBestSeller()
    }

    fun loadFiltered(id: String): LiveData<MutableList<ItemsModel>>{
        return repository.LoadFiltered(id)
    }

}
package com.example.bakery.Repository

import androidx.compose.runtime.mutableStateListOf
import com.example.bakery.Domain.ItemsModel

object FavoritesRepository {
    val favorites = mutableStateListOf<ItemsModel>()

    fun add(item: ItemsModel) {
        if (!isFavorite(item)) {
            favorites.add(item)
        }
    }

    fun remove(item: ItemsModel) {
        favorites.removeAll { it.title == item.title && it.price == item.price }
    }

    fun isFavorite(item: ItemsModel): Boolean {
        return favorites.any { it.title == item.title && it.price == item.price }
    }

    fun toggle(item: ItemsModel): Boolean {
        return if (isFavorite(item)) {
            remove(item)
            false
        } else {
            add(item)
            true
        }
    }
}

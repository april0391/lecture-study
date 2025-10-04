package com.example.mywishlistapp.data

import kotlinx.coroutines.flow.Flow

// Repository는 DAO를 통해 데이터 소스와 상호작용합니다.
class WishRepository(private val wishDao: WishDao) {

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id: Long): Flow<Wish> = wishDao.getWishById(id)

    suspend fun addWish(wish: Wish) {
        wishDao.addWish(wish)
    }

    suspend fun updateWish(wish: Wish) {
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteWish(wish)
    }
}

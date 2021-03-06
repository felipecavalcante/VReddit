package com.rviannaoliveira.vreddit.core.di

import com.rviannaoliveira.vreddit.detail.DetailFragment
import com.rviannaoliveira.vreddit.listing.ListingFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Criado por rodrigo on 29/11/17.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (ServiceModule::class), (RoomModule::class)])
interface VRedditComponent {
    fun inject(listingFragment: ListingFragment)
    fun inject(detailFragment: DetailFragment)
}
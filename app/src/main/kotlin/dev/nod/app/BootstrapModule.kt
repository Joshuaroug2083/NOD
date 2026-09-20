package dev.nod.app

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nod.domain.BuildInfoSource
import dev.nod.model.BootstrapInfo

@Module
@InstallIn(SingletonComponent::class)
object BootstrapModule {
    @Provides
    fun provideBuildInfoSource(): BuildInfoSource = BuildInfoSource { BootstrapInfo() }
}

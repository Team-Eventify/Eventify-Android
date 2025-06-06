package core.common.storages

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SharedStorage

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EncryptedStorage


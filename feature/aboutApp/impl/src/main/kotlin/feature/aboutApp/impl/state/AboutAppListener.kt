package feature.aboutApp.impl.state

interface AboutAppListener {
    fun navigateUp(): Unit
    fun goToAboutUs(): Unit
    fun goPrivacyPolicy(): Unit
    fun goTermsOfUse(): Unit
    fun goToInformationSecurity(): Unit
    fun goToDonate(): Unit
}
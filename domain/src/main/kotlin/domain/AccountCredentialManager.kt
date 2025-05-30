package domain

import android.app.Activity
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import kotlin.getValue


sealed interface SignInResult {
    data object Success: SignInResult
    data object Cancelled: SignInResult
    data object Failure: SignInResult
    data object NoCredentials: SignInResult
}

sealed interface SignUpResult {
    data object Success: SignUpResult
    data object Cancelled: SignUpResult
    data object Failure: SignUpResult
}

typealias Login = String
typealias Password = String

class AccountCredentialManager(
    private val activity: Activity
){
    private val credentialManager by lazy {
        CredentialManager.create(activity)
    }

    suspend fun signUp(login: String, password: String): SignUpResult {
        return try {
            credentialManager.createCredential(
                context = activity,
                request = CreatePasswordRequest(
                    id = login,
                    password = password
                )
            )
            SignUpResult.Success
        } catch (e: CreateCredentialCancellationException) {
            e.printStackTrace()
            SignUpResult.Cancelled
        } catch (e: CreateCredentialException) {
            e.printStackTrace()
            SignUpResult.Failure
        }
    }

    suspend fun signIn(callback: (Login, Password) -> Unit): SignInResult {
        return try {
            val credentialResponse = credentialManager.getCredential(
                context = activity,
                request = GetCredentialRequest(
                    credentialOptions = listOf(GetPasswordOption())
                )
            )

            val credential = credentialResponse.credential as? PasswordCredential
                ?: return SignInResult.Failure

            callback.invoke(
                credential.id,
                credential.password
            )

            SignInResult.Success
        } catch(e: GetCredentialCancellationException) {
            e.printStackTrace()
            SignInResult.Cancelled
        } catch(e: NoCredentialException) {
            e.printStackTrace()
            SignInResult.NoCredentials
        } catch(e: GetCredentialException) {
            e.printStackTrace()
            SignInResult.Failure
        }
    }
}
package com.kitaharaa.monodropper.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitaharaa.monodropper.model.account.AccountInfo
import com.kitaharaa.monodropper.model.transactions.Transaction
import com.kitaharaa.monodropper.mono_api.MonoApiRepository
import com.kitaharaa.monodropper.mono_api.TestTokens.testAccountId
import com.kitaharaa.monodropper.mono_api.TestTokens.testTimeFrom
import com.kitaharaa.monodropper.mono_api.TestTokens.testToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonoViewModel @Inject constructor(
    private val monoApi: MonoApiRepository
) : ViewModel() {
    private val _accountInfoFlow: MutableStateFlow<AccountInfo?> = MutableStateFlow(null)
    val accountInfoFlow = _accountInfoFlow.asStateFlow()

    private val _transactionsFlow: MutableStateFlow<List<Transaction>?> = MutableStateFlow(null)
    val transactions = _transactionsFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _accountInfoFlow.emit(monoApi.getUserInfo(testToken)).also {
                Log.e(TAG, "account info collection: ${accountInfoFlow.value}")
            }

            _transactionsFlow.emit(
                monoApi.getAccountTransaction(
                    testToken,
                    testAccountId,
                    testTimeFrom
                )
            ).also {
                Log.e(TAG, "transaction list collection: ${transactions.value}")
            }
        }
    }

    companion object {
        const val TAG = "MonoViewModel"
    }
}
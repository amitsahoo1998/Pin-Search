package dev.amits.pinsearch.presentation.view.pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.domain.usecases.GetSavedPinUseCases
import dev.amits.pinsearch.domain.usecases.PinResponseUseCases
import dev.amits.pinsearch.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val pinResponseUseCases: PinResponseUseCases,
    private val getSavedPinUseCases: GetSavedPinUseCases
    ) : ViewModel() {
    private val _pinResponse : MutableLiveData<Resource<PinCodeResponse>> = MutableLiveData()
    val pinResponse : LiveData<Resource<PinCodeResponse>>
        get() = _pinResponse

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    //PinCodeApiCall
    fun getPinResponse(pinCodeRequest: PinCodeRequest) = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        _pinResponse.postValue(Resource.Loading())
        _pinResponse.postValue(pinResponseUseCases.execute(pinCodeRequest))
    }

    //Get Data From Db
    fun getSavedPinData() = liveData {
        getSavedPinUseCases.execute().collect(){
            emit(it)
        }
    }
}